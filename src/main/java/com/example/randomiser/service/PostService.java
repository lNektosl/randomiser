package com.example.randomiser.service;

import com.example.randomiser.entity.Response;

import java.io.IOException;
import java.time.LocalDateTime;

public interface PostService {
    LocalDateTime createRandomDate();
    Response randomiseDate();
    Response wrightToFile() throws IOException;
    Response randomComment(int quantity);
    Response CommentsToFile() throws IOException;
    Response GenerateLikes(int quantity);
    Response LikesToFile() throws IOException;
    Response GenerateMessages(int quantity);
    Response MessagesToFile() throws IOException;
    Response generatePosts();
    Response postsToFile() throws IOException;
}
