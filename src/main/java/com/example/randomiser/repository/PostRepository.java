package com.example.randomiser.repository;

import com.example.randomiser.entity.Post;
import com.example.randomiser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByAuthor(User author);
}
