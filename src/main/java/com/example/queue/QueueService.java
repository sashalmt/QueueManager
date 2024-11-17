package com.example.queue;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.stereotype.Service;

/**
 * This class represents a Queue Service.
 * It implements ConcurrentLinkedQueue which is a queue that can handle multiple threads
 * It has enqueue, dequeue and getSize
 */
@Service
public class QueueService{

    private ConcurrentLinkedQueue<Message> queue = new ConcurrentLinkedQueue<>();

    public void enqueue(Message message){
        queue.add(message);
    }

    public Message dequeue(){
        return queue.poll();
    }

    public int getSize(){
        return queue.size();
    }

}