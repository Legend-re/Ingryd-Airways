package com.flywithingryd.IngrydAirways.controller;

import com.flywithingryd.IngrydAirways.dto.request.UserRequest;
import com.flywithingryd.IngrydAirways.dto.response.UserResponse;
import com.flywithingryd.IngrydAirways.service.UserService;
import com.flywithingryd.IngrydAirways.model.Address;
import com.flywithingryd.IngrydAirways.model.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

import static com.flywithingryd.IngrydAirways.controller.ApiEndpoints.USER_CONTROLLER_ENDPOINT;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserRestControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserRestController userRestController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    void registerUser_Success() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("password");

        UserResponse userResponse = new UserResponse();

        when(userService.registerUser(any(UserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(post(USER_CONTROLLER_ENDPOINT + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).registerUser(any(UserRequest.class));
    }

    @Test
    void registerAdmin_Success() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("password");

        UserResponse userResponse = new UserResponse();

        when(userService.registerAdmin(any(UserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(post(USER_CONTROLLER_ENDPOINT + "/registerAdmin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).registerAdmin(any(UserRequest.class));
    }

    @Test
    void loginUser_Success() throws Exception {
        String email = "john.doe@example.com";
        String password = "password";

        UserResponse userResponse = new UserResponse();

        when(userService.loginUser(anyString(), anyString())).thenReturn(userResponse);

        mockMvc.perform(post(USER_CONTROLLER_ENDPOINT + "/login")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).loginUser(anyString(), anyString());
    }

    @Test
    void deleteUser_Success() throws Exception {
        String email = "john.doe@example.com";

        doNothing().when(userService).deleteUserByEmail(anyString());

        mockMvc.perform(delete(USER_CONTROLLER_ENDPOINT + "/{email}", email))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUserByEmail(anyString());
    }

    @Test
    void updateUser_Success() throws Exception {
        String email = "john.doe@example.com";
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setDob(new Date()); // Correct type
        userRequest.setGender(Gender.MALE); // Correct type
        userRequest.setAddress(new Address()); // Correct type

        UserResponse userResponse = new UserResponse();

        when(userService.updateUser(anyString(), any(UserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(put(USER_CONTROLLER_ENDPOINT + "/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).updateUser(anyString(), any(UserRequest.class));
    }
}
