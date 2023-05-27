package com.example.randomiser.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column(name = "date")
    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return author.equals(like.author) && post.equals(like.post);
    }
}
