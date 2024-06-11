package com.flywithingryd.IngrydAirways.service;

import static com.flywithingryd.IngrydAirways.model.enums.Role.ADMIN;
import static com.flywithingryd.IngrydAirways.model.enums.Role.CUSTOMER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.flywithingryd.IngrydAirways.dto.request.UserRequest;
import com.flywithingryd.IngrydAirways.dto.response.UserResponse;
import com.flywithingryd.IngrydAirways.exception.DuplicateEntityException;
import com.flywithingryd.IngrydAirways.exception.UserNotFoundException;
import com.flywithingryd.IngrydAirways.exception.WrongCredentialsException;
import com.flywithingryd.IngrydAirways.mapper.UserMapper;
import com.flywithingryd.IngrydAirways.model.Address;
import com.flywithingryd.IngrydAirways.model.User;
import com.flywithingryd.IngrydAirways.model.enums.Gender;
import com.flywithingryd.IngrydAirways.model.enums.Role;
import com.flywithingryd.IngrydAirways.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_Success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("password");
        userRequest.setDob(new Date()); // Correct type
        userRequest.setGender(Gender.MALE); // Correct type
        userRequest.setAddress(new Address()); // Correct type

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("encodedPassword");
        user.setDob(new Date());
        user.setGender(Gender.MALE);
        user.setAddress(new Address());
        user.setRole(Role.CUSTOMER);

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toUserResponse(any(User.class))).thenReturn(new UserResponse());

        UserResponse userResponse = userService.registerUser(userRequest);

        assertNotNull(userResponse);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerAdmin_Success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Sarah");
        userRequest.setLastName("Doe");
        userRequest.setEmail("sarah.doe@example.com");
        userRequest.setPassword("password");
        userRequest.setDob(new Date()); // Correct type
        userRequest.setGender(Gender.FEMALE); // Correct type
        userRequest.setAddress(new Address()); // Correct type

        User user = new User();
        user.setFirstName("Sarah");
        user.setLastName("Doe");
        user.setEmail("sarah.doe@example.com");
        user.setPassword("encodedPassword");
        user.setDob(new Date());
        user.setGender(Gender.FEMALE);
        user.setAddress(new Address());
        user.setRole(ADMIN);

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toUserResponse(any(User.class))).thenReturn(new UserResponse());

        UserResponse userResponse = userService.registerAdmin(userRequest);

        assertNotNull(userResponse);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerAdmin_WhenUserIsAlreadyAnExistingCustomer() {
        String email = "sarah.doe@example.com";
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setRole(ADMIN);

        User user = new User();
        user.setEmail("sarah.doe@example.com");
        user.setRole(CUSTOMER);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toUserResponse(any(User.class))).thenReturn(new UserResponse());

        UserResponse userResponse = userService.registerAdmin(userRequest);

        assertNotNull(userResponse);
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerAdmin_WhenAdminAlreadyExists() {
        String email = "sarah.doe@example.com";
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setRole(ADMIN);

        User user = new User();
        user.setFirstName("Sarah");
        user.setLastName("Doe");
        user.setEmail(email);
        user.setRole(ADMIN);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        assertThatThrownBy(() -> userService.registerAdmin(userRequest))
                .isExactlyInstanceOf(DuplicateEntityException.class)
                .hasMessageContaining("User is already an Admin");
    }

    @Test
    void loginUser_Success() {
        String email = "john.doe@example.com";
        String password = "password";

        User user = new User();
        user.setEmail(email);
        user.setPassword("password");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, password)).thenReturn(true);
        when(userMapper.toUserResponse(any(User.class))).thenReturn(new UserResponse());

        UserResponse userResponse = userService.loginUser(email, password);

        assertNotNull(userResponse);
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void loginUser_WhenIsPasswordInvalid() {
        String email = "existent@example.com";
        String password = "password";

        User user = new User();
        user.setEmail(email);
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);

        assertThrows(WrongCredentialsException.class, () -> userService.loginUser(email, password));
    }


    @Test
    void loginUser_UserNotFound() {
        String email = "nonexistent@example.com";
        String password = "password";

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.loginUser(email, password));
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void deleteUserByEmail() {
        String email = "john.doe@example.com";

        doNothing().when(userRepository).deleteByEmail(anyString());

        userService.deleteUserByEmail(email);

        verify(userRepository, times(1)).deleteByEmail(anyString());
    }

    @Test
    void updateUser_Success() {
        String email = "john.doe@example.com";

        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setDob(new Date()); // Correct type
        userRequest.setGender(Gender.MALE); // Correct type
        userRequest.setAddress(new Address()); // Correct type

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail(email);
        user.setDob(new Date());
        user.setGender(Gender.MALE);
        user.setAddress(new Address());

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toUserResponse(any(User.class))).thenReturn(new UserResponse());

        UserResponse userResponse = userService.updateUser(email, userRequest);

        assertNotNull(userResponse);
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser_UserNotFound() {
        String email = "nonexistent@example.com";

        UserRequest userRequest = new UserRequest();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(email, userRequest));
        verify(userRepository, times(1)).findByEmail(anyString());
    }
}
