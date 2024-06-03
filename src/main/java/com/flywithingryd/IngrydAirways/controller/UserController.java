package com.flywithingryd.IngrydAirways.controller;

import com.flywithingryd.IngrydAirways.dto.request.UserRequest;
import com.flywithingryd.IngrydAirways.dto.response.UserResponse;
import com.flywithingryd.IngrydAirways.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        logger.info("Registering user with email: {}", userRequest.getEmail());
        UserResponse response = userService.registerUser(userRequest);
        logger.info("User registered successfully with email: {}", userRequest.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestParam String email, @RequestParam String password) {
        logger.info("Logging in user with email: {}", email);
        UserResponse response = userService.loginUser(email, password);
        logger.info("User logged in successfully with email: {}", email);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        logger.info("Deleting user with email: {}", email);
        userService.deleteUserByEmail(email);
        logger.info("User deleted successfully with email: {}", email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String email, @RequestBody UserRequest userRequest) {
        logger.info("Updating user with email: {}", email);
        UserResponse response = userService.updateUser(email, userRequest);
        logger.info("User updated successfully with email: {}", email);
        return ResponseEntity.ok(response);
    }
}