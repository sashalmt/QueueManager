// src/main/java/com/example/queue/service/QueueService.java
package com.example.queue;

import com.example.queue.Message;
import com.example.queue.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QueueService {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public void enqueue(Message message) {
        messageRepository.save(message);
    }

    @Transactional
    public Message dequeue() {
        Message message = messageRepository.findFirstByOrderByIdAsc();
        if (message == null) {
            System.out.println("Queue is Empty");
            return null;
        }
        messageRepository.delete(message);
        return message;
    }

    public long getQueueSize() {
        return messageRepository.count();
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
