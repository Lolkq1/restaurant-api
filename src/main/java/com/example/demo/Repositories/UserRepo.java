package com.example.demo.Repositories;

import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    public Optional<User> findUserByEmail(String email);

}