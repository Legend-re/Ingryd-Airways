//package com.flywithingryd.IngrydAirways.controller;
//
//import com.flywithingryd.IngrydAirways.dto.request.UserRequest;
//import com.flywithingryd.IngrydAirways.dto.response.UserResponse;
//
//import com.flywithingryd.IngrydAirways.service.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import static com.flywithingryd.IngrydAirways.controller.ApiEndpoints.USER_CONTROLLER_ENDPOINT;
//
//
//@RestController
//@RequestMapping(USER_CONTROLLER_ENDPOINT)
//@Tag(name = "User Management", description = "API for managing user registration, login, and profile updates.")
//public class UserRestController {
//    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
//
//    private final UserService userService;

//    @Autowired
//    public UserRestController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @PostMapping("/register")
//    @Operation(summary = "Register User", description = "Register a new user.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User registered successfully",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input",
//                    content = @Content)
//    })
//    public ResponseEntity<UserResponse> registerUser(
//            @Parameter(description = "User registration details", required = true)
//            @RequestBody UserRequest userRequest) {
//        logger.info("Registering user with email: {}", userRequest.getEmail());
//        UserResponse response = userService.registerUser(userRequest);
//        logger.info("User registered successfully with email: {}", userRequest.getEmail());
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/registerAdmin")
//    @PreAuthorize("hasRole('ADMIN')")
//    @Operation(summary = "Register Admin", description = "Register a new admin user.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Admin registered successfully",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input",
//                    content = @Content)
//    })
//    public ResponseEntity<UserResponse> registerAdmin(
//            @Parameter(description = "Admin registration details", required = true)
//            @RequestBody UserRequest userRequest) {
//        logger.info("Registering admin with email: {}", userRequest.getEmail());
//        UserResponse response = userService.registerAdmin(userRequest);
//        logger.info("Admin registered successfully with email: {}", userRequest.getEmail());
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/login")
//    @Operation(summary = "Login User", description = "Log in an existing user.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User logged in successfully",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid email or password",
//                    content = @Content)
//    })
//    public ResponseEntity<UserResponse> loginUser(
//            @Parameter(description = "User email", required = true) @RequestParam String email,
//            @Parameter(description = "User password", required = true) @RequestParam String password) {
//        logger.info("Logging in user with email: {}", email);
//        UserResponse response = userService.loginUser(email, password);
//        logger.info("User logged in successfully with email: {}", email);
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/{email}")
//    @Operation(summary = "Delete User", description = "Delete a user by email.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "User deleted successfully",
//                    content = @Content),
//            @ApiResponse(responseCode = "404", description = "User not found",
//                    content = @Content)
//    })
//    public ResponseEntity<Void> deleteUser(
//            @Parameter(description = "User email", required = true) @PathVariable String email) {
//        logger.info("Deleting user with email: {}", email);
//        userService.deleteUserByEmail(email);
//        logger.info("User deleted successfully with email: {}", email);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping("/{email}")
//    @Operation(summary = "Update User", description = "Update user details by email.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User updated successfully",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input",
//                    content = @Content),
//            @ApiResponse(responseCode = "404", description = "User not found",
//                    content = @Content)
//    })
//    public ResponseEntity<UserResponse> updateUser(
//            @Parameter(description = "User email", required = true) @PathVariable String email,
//            @Parameter(description = "Updated user details", required = true) @RequestBody UserRequest userRequest) {
//        logger.info("Updating user with email: {}", email);
//        UserResponse response = userService.updateUser(email, userRequest);
//        logger.info("User updated successfully with email: {}", email);
//        return ResponseEntity.ok(response);
//    }
//}