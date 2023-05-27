package com.example.randomiser.repository;

import com.example.randomiser.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
