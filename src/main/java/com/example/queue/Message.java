package com.example.queue;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime enqueueTime;

    // default constructor required by JPA
    public Message() {
        this.enqueueTime = LocalDateTime.now();
    }

    public Message(String content) {
        this.content = content;
        this.enqueueTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getEnqueueTime() {
        return enqueueTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEnqueueTime(LocalDateTime enqueueTime) {
        this.enqueueTime = enqueueTime;
    }
}
