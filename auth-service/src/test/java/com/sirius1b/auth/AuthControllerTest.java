package com.sirius1b.auth;

import com.sirius1b.auth.controllers.AuthController;
import com.sirius1b.auth.dtos.*;
import com.sirius1b.auth.exceptions.*;
import com.sirius1b.auth.models.Token;
import com.sirius1b.auth.models.User;
import com.sirius1b.auth.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signup_success() throws RoleNotFoundException, UserExistsException {
        RegisterDto registerDto = new RegisterDto("testUser", "test@example.com", "password");
        User mockUser = new User();
        mockUser.setName("testUser");
        mockUser.setEmail("test@example.com");

        when(userService.register(anyString(), anyString(), anyString())).thenReturn(mockUser);

        RegisterRespDto response = authController.signup(registerDto);

        assertNotNull(response);
        assertEquals("testUser", response.getName());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void signup_userExistsException() throws RoleNotFoundException, UserExistsException {
        RegisterDto registerDto = new RegisterDto("testUser", "test@example.com", "password");

        when(userService.register(anyString(), anyString(), anyString())).thenThrow(new UserExistsException("User already exists"));

        assertThrows(UserExistsException.class, () -> authController.signup(registerDto));
    }

    @Test
    void login_success() throws UserNotFoundException, CredentialException {
        LoginDto loginDto = new LoginDto("test@example.com", "password");
        Token mockToken = new Token();
        mockToken.setValue("mockTokenValue");

        when(userService.login(anyString(), anyString())).thenReturn(mockToken);

        TokenRespDto response = authController.login(loginDto);

        assertNotNull(response);
        assertEquals("mockTokenValue", response.getToken());
    }

    @Test
    void login_userNotFoundException() throws UserNotFoundException, CredentialException {
        LoginDto loginDto = new LoginDto("test@example.com", "password");

        when(userService.login(anyString(), anyString())).thenThrow(new UserNotFoundException("User not found"));

        assertThrows(UserNotFoundException.class, () -> authController.login(loginDto));
    }

    @Test
    void logout_success() throws TokenNotFoundException {
        TokenDto tokenDto = new TokenDto("testToken");

        authController.logout(tokenDto);

        verify(userService, times(1)).logout("testToken");
    }

    @Test
    void logout_tokenNotFoundException() throws TokenNotFoundException {
        TokenDto tokenDto = new TokenDto("testToken");

        doThrow(new TokenNotFoundException("Token not found")).when(userService).logout("testToken");

        assertThrows(TokenNotFoundException.class, () -> authController.logout(tokenDto));
    }

    @Test
    void verify_token_with_authorization_header_success() throws TokenNotFoundException {
        String token = "Bearer mockToken";
        String role = "ADMIN";

        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");

        when(userService.extractRoles("mockToken")).thenReturn(roles);

        Boolean result = authController.verify(token, role);

        assertTrue(result);
    }

    @Test
    void verify_token_with_authorization_header_role_not_found() throws TokenNotFoundException {
        String token = "Bearer mockToken";
        String role = "ADMIN";

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        when(userService.extractRoles("mockToken")).thenReturn(roles);

        Boolean result = authController.verify(token, role);

        assertFalse(result);
    }

    @Test
    void verify_token_with_authorization_header_no_role_provided() throws TokenNotFoundException {
        String token = "Bearer mockToken";
        String role = null;

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        when(userService.extractRoles("mockToken")).thenReturn(roles);

        Boolean result = authController.verify(token, role);

        assertTrue(result);
    }


    @Test
    void verifyToken_validTokenAndRole_returnsTrue() {
        String token = "validToken";
        String role = "ADMIN";
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        when(userService.extractRoles(anyString())).thenReturn(roles);
        AuthController controller = new AuthController(userService);

        // Use reflection to access the private method
        try {
            java.lang.reflect.Method method = AuthController.class.getDeclaredMethod("verifyToken", String.class, String.class);
            method.setAccessible(true);
            Boolean result = (Boolean) method.invoke(controller, token, role);
            assertTrue(result);
        } catch (Exception e) {
            fail("Exception occurred while invoking method: " + e.getMessage());
        }
    }

    @Test
    void verifyToken_validTokenAndIncorrectRole_returnsFalse() {
        String token = "validToken";
        String role = "ADMIN";
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        when(userService.extractRoles(token)).thenReturn(roles);
        AuthController controller = new AuthController(userService);


        try {
            java.lang.reflect.Method method = AuthController.class.getDeclaredMethod("verifyToken", String.class, String.class);
            method.setAccessible(true);
            Boolean result = (Boolean) method.invoke(controller, token, role);
            assertFalse(result);
        } catch (Exception e) {
            fail("Exception occurred while invoking method: " + e.getMessage());
        }
    }
}