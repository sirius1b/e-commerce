package e_commerce.cart_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import e_commerce.cart_service.dto.AddItemRequest;
import e_commerce.cart_service.dto.CheckoutResponse;
import e_commerce.cart_service.dto.RemoveItemRequest;
import e_commerce.cart_service.exception.CartEmptyException;
import e_commerce.cart_service.exception.CheckoutException;
import e_commerce.cart_service.model.Cart;
import e_commerce.cart_service.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestHeader("Authorization") String token) {
        Cart cart = cartService.getCart(token);
        return ResponseEntity.ok(cart);
    }
    
    @PostMapping("/cart/add")
    public ResponseEntity<Cart> addItemToCart(
            @RequestHeader("Authorization") String token,
            @RequestBody AddItemRequest request) {
        Cart cart = cartService.addItemToCart(token, request);
        return ResponseEntity.ok(cart);
    }
    
    @PostMapping("/cart/remove")
    public ResponseEntity<Cart> removeItemFromCart(
            @RequestHeader("Authorization") String token,
            @RequestBody RemoveItemRequest request) {
        Cart cart = cartService.removeItemFromCart(token, request.getSkuId());
        return ResponseEntity.ok(cart);
    }
    
    @PostMapping("/cart/checkout")
    public ResponseEntity<?> checkout(@RequestHeader("Authorization") String token) {
        try {
            CheckoutResponse response = cartService.checkout(token);
            return ResponseEntity.ok(response);
        } catch (CartEmptyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CheckoutException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}