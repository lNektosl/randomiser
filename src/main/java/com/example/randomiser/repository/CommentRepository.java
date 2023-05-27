package com.example.randomiser.repository;

import com.example.randomiser.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
