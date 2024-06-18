package com.flywithingryd.IngrydAirways.config;

import com.flywithingryd.IngrydAirways.model.User;
import com.flywithingryd.IngrydAirways.model.enums.Gender;
import com.flywithingryd.IngrydAirways.model.enums.Role;
import com.flywithingryd.IngrydAirways.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class DefaultAdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
            // Create a new user
            User user = new User();
            user.setRole(Role.ADMIN);
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setGender(Gender.FEMALE);
            user.setDob(Date.from(Instant.now()));
            user.setEmail("admin@admin.com");
            user.setPassword(passwordEncoder.encode("password")); // Encode the password
            userRepository.save(user);
        }
    }
}
