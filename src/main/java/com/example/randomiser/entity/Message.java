package com.example.randomiser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "messages")
@Data
@NoArgsConstructor
public class Message {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "reciever_id", referencedColumnName = "id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "parent_message_id", referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private Message parentMessage;

    @Column(name = "date")
    private LocalDateTime date;

}

