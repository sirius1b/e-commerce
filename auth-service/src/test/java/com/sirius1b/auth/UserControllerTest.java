package com.sirius1b.auth;

import com.sirius1b.auth.controllers.UserController;
import com.sirius1b.auth.dtos.UserInfoDto;
import com.sirius1b.auth.exceptions.UserNotFoundException;
import com.sirius1b.auth.services.JwtService;
import com.sirius1b.auth.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInfo_success() throws UserNotFoundException {
        String token = "Bearer testToken";
        String email = "test@example.com";
        UserInfoDto mockUserInfoDto = new UserInfoDto();
        mockUserInfoDto.setName("testUser");
        mockUserInfoDto.setEmail("test@example.com");

        when(jwtService.extractUsername("testToken")).thenReturn(email);
        when(userService.getUser(email)).thenReturn(mockUserInfoDto);

        UserInfoDto response = userController.getInfo(token);

        assertEquals("testUser", response.getName());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void getInfo_userNotFoundException() throws UserNotFoundException {
        String token = "Bearer testToken";
        String email = "test@example.com";

        when(jwtService.extractUsername("testToken")).thenReturn(email);
        when(userService.getUser(email)).thenThrow(new UserNotFoundException("User not found"));

        assertThrows(UserNotFoundException.class, () -> userController.getInfo(token));
    }

    @Test
    void updateInfo_success() throws UserNotFoundException {
        String token = "Bearer testToken";
        String email = "test@example.com";
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setName("newTestUser");
        userInfoDto.setEmail("newTest@example.com");

        when(jwtService.extractUsername("testToken")).thenReturn(email);

        userController.updateInfo(token, userInfoDto);

        verify(userService, times(1)).updateUser(email, "newTestUser", "newTest@example.com");
    }

    @Test
    void updateInfo_userNotFoundException() throws UserNotFoundException {
        String token = "Bearer testToken";
        String email = "test@example.com";
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setName("newTestUser");
        userInfoDto.setEmail("newTest@example.com");

        when(jwtService.extractUsername("testToken")).thenReturn(email);
        doThrow(new UserNotFoundException("User not found")).when(userService).updateUser(email, "newTestUser", "newTest@example.com");

        assertThrows(UserNotFoundException.class, () -> userController.updateInfo(token, userInfoDto));
    }
}