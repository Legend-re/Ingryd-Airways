package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.dto.request.UserRequest;
import com.flywithingryd.IngrydAirways.dto.response.UserResponse;
import com.flywithingryd.IngrydAirways.exception.UserNotFoundException;
import com.flywithingryd.IngrydAirways.mapper.UserMapper;
import com.flywithingryd.IngrydAirways.model.User;
import com.flywithingryd.IngrydAirways.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserResponse loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return userMapper.toUserResponse(user);
        } else {
            throw new UserNotFoundException("Invalid email or password");
        }
    }


    public User findUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users registered or exist yet");
        }
        return users;
    }

    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        userRepository.delete(user);
    }

    public UserResponse updateUser(String email, UserRequest userRequest) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setDob(userRequest.getDob());
        user.setGender(userRequest.getGender());
        user.setAddress(userRequest.getAddress());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    public UserResponse registerUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setDob(userRequest.getDob());
        user.setGender(userRequest.getGender());
        user.setAddress(userRequest.getAddress());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

}
