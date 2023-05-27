package com.example.randomiser.repository;

import com.example.randomiser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface UserRepository extends JpaRepository<User, UUID> {
}
