package e_commerce.cart_service.controller;

import e_commerce.cart_service.client.AuthServiceClient;
import e_commerce.cart_service.dto.request.AddItemRequest;
import e_commerce.cart_service.dto.request.RemoveItemRequest;
import e_commerce.cart_service.exception.UnauthorizedException;
import e_commerce.cart_service.model.Cart;
import e_commerce.cart_service.service.CartService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthServiceClient authServiceClient;

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestHeader("Authorization") String token) throws Exception {
        if (!authServiceClient.verifyToken(token, "USER")) {
            throw new UnauthorizedException("Invalid token");
        }
        String userId = authServiceClient.getUserInfoFromToken(token).getEmail();
        Cart cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addItemToCart(
            @RequestHeader("Authorization") String token,
            @RequestBody AddItemRequest request) throws Exception {
        if (!authServiceClient.verifyToken(token, "USER")) {
            throw new Exception("Invalid token");
        }
        log.info("token has been verified");
        String userId = authServiceClient.getUserInfoFromToken(token).getEmail();
        log.info("userId has been extracted from token: {}", userId);
        Cart cart = cartService.addItemToCart(userId, request);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/remove")
    public ResponseEntity<Cart> removeItemFromCart(
            @RequestHeader("Authorization") String token,
            @RequestBody RemoveItemRequest request) throws Exception {
        if (!authServiceClient.verifyToken(token, "USER")) {
            throw new Exception("Invalid token");
        }
        log.info("token has been verified for removing item from cart");
        String userId = authServiceClient.getUserInfoFromToken(token).getEmail();
        Cart cart = cartService.removeItemFromCart(userId, request.getSkuId());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkoutCart(@RequestHeader("Authorization") String token) throws Exception {
        if (!authServiceClient.verifyToken(token,"USER")) {
            throw new Exception("Invalid token");
        }
        log.info("token has been verified for checkout");
        String userId = authServiceClient.getUserInfoFromToken(token).getEmail();
        String checkoutResponse = cartService.checkoutCart(userId);
        return ResponseEntity.ok(checkoutResponse);
    }
}