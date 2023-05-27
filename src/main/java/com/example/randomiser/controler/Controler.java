package com.example.randomiser.controler;

import com.example.randomiser.entity.Message;
import com.example.randomiser.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.randomiser.service.PostService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/test")
public class Controler {
    private final PostService postService;

    @Autowired
    public Controler(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/simple")
    public String simple() {
        return "hello world!";
    }

    @GetMapping("/date")
    private LocalDateTime getDate() {
        return postService.createRandomDate();
    }

    @GetMapping("/asd")
    private Response getId() {
        return postService.randomiseDate();
    }

    @GetMapping("/wright")
    private Response wright() throws IOException {
        return postService.wrightToFile();
    }

    @GetMapping("/comments")
    private Response getComments() {
        return postService.randomComment(1000);
    }

    @GetMapping("/likes")
    private Response getLikes() {
        return postService.GenerateLikes(200);
    }
    @GetMapping("/messages")
    private Response getMessages(){
        return postService.GenerateMessages(50);
    }
    @GetMapping("/cWright")
    private Response wrightComments() throws IOException {
        return postService.CommentsToFile();
    }
    @GetMapping("/lWright")
    private Response wrightLikes() throws IOException {
        return postService.LikesToFile();
    }
    @GetMapping("/mWright")
    private Response messagesWright() throws IOException {
        return postService.MessagesToFile();
    }
    @GetMapping("/post")
    private Response genPosts(){
        return postService.generatePosts();
    }
    @GetMapping("/pWright")
    private Response pWright() throws IOException {
        return postService.postsToFile();
    }
}
