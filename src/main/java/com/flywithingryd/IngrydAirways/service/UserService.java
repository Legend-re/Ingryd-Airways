package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.dto.request.UserRequest;
import com.flywithingryd.IngrydAirways.dto.response.UserResponse;
import com.flywithingryd.IngrydAirways.exception.UserNotFoundException;
import com.flywithingryd.IngrydAirways.mapper.UserMapper;
import com.flywithingryd.IngrydAirways.model.User;
import com.flywithingryd.IngrydAirways.model.enums.Role;
import com.flywithingryd.IngrydAirways.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        logger.info("Registering user with email: {}", userRequest.getEmail());
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setDob(userRequest.getDob());
        user.setGender(userRequest.getGender());
        user.setAddress(userRequest.getAddress());
        user.setRole(Role.CUSTOMER);

        User savedUser = userRepository.save(user);
        logger.info("User registered successfully with email: {}", userRequest.getEmail());
        return userMapper.toUserResponse(savedUser);
    }

    public UserResponse loginUser(String email, String password) {
        logger.info("Attempting login for user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        if (passwordEncoder.matches(password, user.getPassword())) {
            logger.info("User logged in successfully with email: {}", email);
            return userMapper.toUserResponse(user);
        } else {
            logger.warn("Invalid login attempt for user with email: {}", email);
            throw new UserNotFoundException("Invalid email or password");
        }
    }

    public void deleteUserByEmail(String email) {
        logger.info("Deleting user with email: {}", email);
        userRepository.deleteByEmail(email);
        logger.info("User deleted successfully with email: {}", email);
    }

    public UserResponse updateUser(String email, UserRequest userRequest) {
        logger.info("Updating user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setDob(userRequest.getDob());
        user.setGender(userRequest.getGender());
        user.setAddress(userRequest.getAddress());

        User updatedUser = userRepository.save(user);
        logger.info("User updated successfully with email: {}", email);
        return userMapper.toUserResponse(updatedUser);
    }
}