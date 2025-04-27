package com.sirius1b.product;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sirius1b.product.controllers.ProductController;
import com.sirius1b.product.dtos.ProductDto;
import com.sirius1b.product.exceptions.InternalException;
import com.sirius1b.product.services.ProductService;
import com.sirius1b.product.services.TokenVerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private TokenVerificationService verificationService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_success() throws JsonProcessingException {
        List<ProductDto> mockProducts = new ArrayList<>();
        mockProducts.add(new ProductDto());
        mockProducts.add(new ProductDto());

        when(productService.getAllProducts(null, null, 0, 10)).thenReturn(mockProducts);

        ResponseEntity<List<ProductDto>> response = productController.getAllProducts(null, null, 0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getProductById_success() {
        ProductDto mockProduct = new ProductDto();
        mockProduct.setId("testId");

        when(productService.getProductById("testId")).thenReturn(mockProduct);

        ResponseEntity<ProductDto> response = productController.getProductById("testId");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testId", response.getBody().getId());
    }

    @Test
    void getProductById_notFound() {
        when(productService.getProductById("testId")).thenReturn(null);

        ResponseEntity<ProductDto> response = productController.getProductById("testId");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createProduct_success() throws JsonProcessingException, InternalException {
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        String token = "testToken";

//        doNothing().when(verificationService).verifyToken(token);
        when(verificationService.verifyToken(any())).thenReturn(true);
        when(productService.createProduct(any(ProductDto.class))).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.createProduct(productDto, token);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
        assertEquals("Test Product", response.getBody().getName());
        verify(verificationService, times(1)).verifyToken(token);
    }

     @Test
    void createProduct_verificationFails() throws JsonProcessingException, InternalException {
        ProductDto productDto = new ProductDto();
        String token = "invalidToken";

        doThrow(new InternalException("Unauthorized")).when(verificationService).verifyToken(token);

        assertThrows(InternalException.class, () -> productController.createProduct(productDto, token));
    }


    @Test
    void updateProduct_success() throws InternalException {
        String id = "testId";
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName("Updated Product");
        String token = "testToken";

        when(verificationService.verifyToken(any())).thenReturn(true);
        when(productService.updateProduct(any(ProductDto.class))).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.updateProduct(id, productDto, token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
        assertEquals("Updated Product", response.getBody().getName());
        verify(verificationService, times(1)).verifyToken(token);
    }

    @Test
    void updateProduct_notFound() throws InternalException {
        String id = "testId";
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        String token = "testToken";

        when(verificationService.verifyToken(any())).thenReturn(true);
        when(productService.updateProduct(any(ProductDto.class))).thenReturn(null);

        ResponseEntity<ProductDto> response = productController.updateProduct(id, productDto, token);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(verificationService, times(1)).verifyToken(token);
    }

     @Test
    void updateProduct_verificationFails() throws JsonProcessingException, InternalException {
        ProductDto productDto = new ProductDto();
        String token = "invalidToken";

        doThrow(new InternalException("Unauthorized")).when(verificationService).verifyToken(token);

        assertThrows(InternalException.class, () -> productController.createProduct(productDto, token));
    }

    @Test
    void deleteProductById_success() throws InternalException {
        String id = "testId";
        String token = "testToken";

        when(verificationService.verifyToken(any())).thenReturn(true);
        doNothing().when(productService).deleteProductById(id);

        ResponseEntity<Void> response = productController.deleteProductById(id, token);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProductById(id);
        verify(verificationService, times(1)).verifyToken(token);
    }

     @Test
    void deleteProductById_verificationFails() throws JsonProcessingException, InternalException {

         String id = "testId";
        String token = "invalidToken";

        doThrow(new InternalException("Unauthorized")).when(verificationService).verifyToken(token);

        assertThrows(InternalException.class, () -> productController.deleteProductById(id, token));
    }
}