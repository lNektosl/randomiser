package com.example.randomiser.repository;

import com.example.randomiser.entity.Like;
import com.example.randomiser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    List<Like> findByAuthor(User Author);
}
