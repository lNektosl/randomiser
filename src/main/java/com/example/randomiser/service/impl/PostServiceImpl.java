package com.example.randomiser.service.impl;

import com.example.randomiser.entity.Comment;
import com.example.randomiser.entity.Like;
import com.example.randomiser.entity.Message;
import com.example.randomiser.entity.Post;
import com.example.randomiser.entity.Response;
import com.example.randomiser.entity.User;
import com.example.randomiser.repository.CommentRepository;
import com.example.randomiser.repository.LikeRepository;
import com.example.randomiser.repository.MessageRepository;
import com.example.randomiser.repository.PostRepository;
import com.example.randomiser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.randomiser.service.PostService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final MessageRepository messageRepository;
    private final String loremHolder = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dictum nec lorem eu sodales. Nullam et commodo ante. Vivamus elementum non ante vel egestas. Fusce non euismod velit. Aliquam erat volutpat. Sed molestie, mi ullamcorper rutrum porttitor, tellus est lobortis lorem, non luctus libero justo ac mauris. Aliquam rhoncus tellus non arcu scelerisque, at venenatis neque vulputate. Aliquam fringilla elementum congue. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras in diam est. Pellentesque porttitor ipsum et urna dapibus, ac mollis felis posuere. In risus dolor, varius at metus eu, maximus tempor purus. Phasellus posuere nibh at tellus mollis, sed semper turpis bibendum. Proin lobortis justo nec rutrum pretium. Nulla fringilla tristique pellentesque. Mauris odio dolor, elementum a tortor rhoncus, sodales imperdiet dui. Quisque elit lorem, interdum eget arcu at, pulvinar luctus sem. Proin porttitor placerat ipsum, eu faucibus eros aliquet a. Cras finibus porta odio. Vestibulum porttitor feugiat elit, sit amet faucibus justo bibendum sit amet. Suspendisse ornare, arcu ac rutrum laoreet, massa dui rhoncus tellus, ac consequat risus libero ac nisi. Vestibulum id quam massa.";


    public Response generatePosts(){
        for(int i = 0;i<100;i++){
            postRepository.save(Post.builder()
                    .id(UUID.randomUUID())
                    .author(userRepository.findAll().get(createRandomIntBetween(0,userRepository.findAll().size()-1)))
                    .title(loremHolder.substring(1,createRandomIntBetween(1,45)))
                            .text(loremHolder.substring(1,createRandomIntBetween(100,500)))
                    .publicationDate(createRandomDate())
                    .build()
            );
        }
        return Response.builder()
                .Done("done!")
                .build();
    }

    @Override
    public Response postsToFile() throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter("Posts.sql"));
        bf.write("INSERT INTO posts VALUES");
        for (Post post : postRepository.findAll())
            bf.write("\n('" + post.getId() + "','" + post.getAuthor().getId() + "','" + post.getTitle() + "','" + post.getText() + "','" + post.getPublicationDate() + "'),");

        bf.close();
        return Response.builder()
                .Done("done!")
                .build();
    }

    @Override
    public Response randomiseDate() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            post.setPublicationDate(createRandomDate());
            postRepository.save(post);
        }
        return Response.builder()
                .Done("done!")
                .build();
    }

    @Override
    public Response wrightToFile() throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter("posts.sql"));
        for (Post post : postRepository.findAll())
            bf.write("\n('" + post.getId() + "','" + post.getAuthor().getId() + "','" + post.getTitle() + "','" + post.getText() + "','" + post.getPublicationDate() + "'),");
        bf.close();
        return Response.builder()
                .Done("done")
                .build();
    }

    public Response randomComment(int quantity) {
        List<User> userIds = userRepository.findAll();
        List<Post> postsIds = postRepository.findAll();
        for (int i = 0; i < quantity; i++) {
            commentRepository.save(Comment.builder()
                    .id(UUID.randomUUID())
                    .author(userIds.get(createRandomIntBetween(0, (userIds.size()-1))))
                    .post(postsIds.get(createRandomIntBetween(0, (postsIds.size()-1))))
                    .text(loremHolder.substring(0, createRandomIntBetween(5, 200)))
                    .date(createRandomDate())
                    .build());
        }
        return Response.builder()
                .Done("done")
                .build();
    }

    public Response CommentsToFile() throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter("Comments.sql"));
        bf.write("INSERT INTO comments(id,author_id,post_id,text,date) VALUES");
        for (Comment comment : commentRepository.findAll())
            bf.write("\n('" + comment.getId() + "','" + comment.getAuthor().getId() + "','" + comment.getPost().getId() + "','" + comment.getText() + "','" + comment.getDate() + "'),");
        bf.close();
        return Response.builder()
                .Done("done")
                .build();
    }

    public Response GenerateLikes(int quantity) {
        List<Like> likes = likeRepository.findAll();
        List<User> users = userRepository.findAll();
        List<Post> posts = postRepository.findAll();
        for (int i = 0; i < quantity; i++) {
            Post post = posts.get(createRandomIntBetween(0, posts.size()));
            Like like = Like.builder()
                    .id(UUID.randomUUID())
                    .author(users.get(createRandomIntBetween(0, users.size())))
                    .post(post)
                    .date(createRandomDate(post.getPublicationDate()))
                    .build();
            if (likes.contains(like)) {
                System.out.println("Повтор");
                break;
            }
            likes.add(like);
            likeRepository.save(like);
        }
        return Response.builder()
                .Done("done")
                .build();
    }

    public Response LikesToFile() throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter("Likes.sql"));
        bf.write("INSERT INTO likes(id,author_id,post_id,date) VALUES");
        for (Like like : likeRepository.findAll())
            bf.write("\n('" + like.getId() + "','" + like.getAuthor().getId() + "','" + like.getPost().getId() + "','" + like.getDate() + "'),");
        bf.close();
        return Response.builder()
                .Done("done")
                .build();
    }

    @Override
    public Response GenerateMessages(int quantity) {
        List<User> users = userRepository.findAll();
        for (int i = 0; i < quantity; i++) {
            User sender = users.get(createRandomIntBetween(0, users.size() - 1));
            User receiver = users.get(createRandomIntBetween(0, users.size() - 1));
            Message lastMessage = null;
            Message message = Message.builder()
                    .id(UUID.randomUUID())
                    .text(loremHolder.substring(0, createRandomIntBetween(10, 500)))
                    .sender(sender)
                    .receiver(receiver)
                    .date(createRandomDate())
                    .build();
            messageRepository.save(message);
            lastMessage = message;
            int a = createRandomIntBetween(0, 10);
            for (int x = 0; x < a; x++) {
                User temp = sender;
                sender = receiver;
                receiver = temp;
                message = Message.builder()
                        .id(UUID.randomUUID())
                        .text(loremHolder.substring(0, createRandomIntBetween(10, 500)))
                        .sender(sender)
                        .receiver(receiver)
                        .parentMessage(lastMessage)
                        .date(createRandomDate(lastMessage.getDate()))
                        .build();
                messageRepository.save(message);
                lastMessage = message;
            }
        }
        return Response.builder()
                .Done("done")
                .build();
    }

    @Override
    public Response MessagesToFile() throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter("messages.sql"));
        bf.write("INSERT INTO messages(id,text,sender_id,reciever_id,parent_message_id,date)) VALUE");
        for (Message message : messageRepository.findAll()) {
            String holder;
            try {
                holder=message.getParentMessage().getId().toString();
            }catch (NullPointerException e){
                holder = "NULL";
            }
            bf.write("\n('" + message.getId() +
                    "','" + message.getText() +
                    "','" + message.getSender().getId() +
                    "','" + message.getReceiver().getId() +
                    "','" + holder +
                    "','" + message.getDate() + "'),");
        }
        bf.close();
        return Response.builder()
                .Done("done")
                .build();
    }


    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public LocalDateTime createRandomDate() {
        int seconds = createRandomIntBetween(0, 59);
        int minute = createRandomIntBetween(0, 59);
        int hour = createRandomIntBetween(0, 23);
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(2020, 2023);
        return LocalDateTime.of(year, month, day, hour, minute, seconds);
    }

    public LocalDateTime createRandomDate(LocalDateTime dateTime) {
        int seconds = createRandomIntBetween(dateTime.getSecond(), 59);
        int minute = createRandomIntBetween(dateTime.getMinute(), 59);
        int hour = createRandomIntBetween(dateTime.getHour(), 23);
        int day = createRandomIntBetween(dateTime.getDayOfMonth(), 28);
        int month = createRandomIntBetween(dateTime.getMonthValue(), 12);
        int year = createRandomIntBetween(dateTime.getYear(), 2023);
        return LocalDateTime.of(year, month, day, hour, minute, seconds);
    }

}
