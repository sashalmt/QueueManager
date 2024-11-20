// src/main/java/com/example/queue/repository/MessageRepository.java
package com.example.queue;

import com.example.queue.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Find the oldest message based on id
    Message findFirstByOrderByIdAsc();

    // Alternative using an enqueue time ??
}
