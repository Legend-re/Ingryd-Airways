package com.flywithingryd.IngrydAirways.controller;

import com.flywithingryd.IngrydAirways.dto.request.UserLoginRequest;
import com.flywithingryd.IngrydAirways.dto.request.UserRequest;
import com.flywithingryd.IngrydAirways.dto.response.UserResponse;
import com.flywithingryd.IngrydAirways.exception.UserNotFoundException;
import com.flywithingryd.IngrydAirways.model.User;
import com.flywithingryd.IngrydAirways.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@Validated
@Tag(name = "User API", description = "API for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user with the provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user details provided")
    })
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) {
        logger.info("Registering new user with email: {}", userRequest.getEmail());
        UserResponse userResponse = userService.registerUser(userRequest);
        logger.info("User registration successful.");
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate user and return user details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<UserResponse> loginUser(@RequestBody @Valid UserLoginRequest loginRequest) {
        logger.info("Login attempt for email: {}", loginRequest.getEmail());
        UserResponse userResponse = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve user details by user ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.debug("Retrieving user with ID: {}", id);
        User user = userService.findUserById(id);
        if (user == null) {
            logger.warn("User with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email", description = "Retrieve user details by email.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        logger.debug("Retrieving user with email: {}", email);
        User user = userService.findUserByEmail(email);
        if (user == null) {
            logger.warn("User with email: {} not found", email);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve all registered users.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users found"),
            @ApiResponse(responseCode = "204", description = "No users found")
    })
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            logger.info("No users found");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/email/{email}")
    @Operation(summary = "Delete a user by email", description = "Delete a user by their email.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        logger.info("Deleting user with email: {}", email);
        boolean userExists = userService.userExistsByEmail(email);
        if (!userExists) {
            logger.warn("User with email: {} not found", email);
            return ResponseEntity.notFound().build();
        }
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/email/{email}")
    @Operation(summary = "Update a user by email", description = "Update a user's details by their email.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResponse> updateUser(@PathVariable String email, @RequestBody @Valid UserRequest userRequest) {
        logger.info("Updating user with email: {}", email);
        UserResponse userResponse = userService.updateUser(email, userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Operation(summary = "Handle UserNotFoundException", description = "Handle user not found exception and return a 404 status.")
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error("User not found exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}












