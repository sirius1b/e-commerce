package com.sirius1b.product.filters;


import com.sirius1b.product.services.TokenVerificationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;

@Slf4j
@Component
public class TokenVerificationFilter extends OncePerRequestFilter {
    // TODO: update this, for only only admin api verify
    @Autowired
        private TokenVerificationService tokenVerificationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

            try {
                if (tokenVerificationService.verifyToken(token)) {
                    filterChain.doFilter(request, response); // Token is valid, continue
                } else {
                    log.warn("Invalid token provided: {}", token);
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
                }
            } catch (Exception e) {
                log.error("Error verifying token: {}", e.getMessage());
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Token verification failed");
            }

        } else {
            log.warn("Authorization header missing or invalid.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid authorization header");
        }
    }
}