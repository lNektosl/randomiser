package com.example.randomiser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name ="id")
    private UUID id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "hashed_password")
    private String password;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    @JsonIgnore
    private List<Comment> comments;

    @Column(name = "date_of_birth")
    private LocalDateTime dayOfBirth;

}
