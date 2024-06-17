package com.flywithingryd.IngrydAirways.repository;

import com.flywithingryd.IngrydAirways.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Long id);
    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}
