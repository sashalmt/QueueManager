package com.example.queue;

import com.example.queue.Message;
import com.example.queue.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @Autowired
    private ProcessingService processingService;

    @PostMapping("/enqueue")
    public ResponseEntity<String> enqueue(@RequestBody Message message) {
        queueService.enqueue(message);
        return ResponseEntity.ok("Enqueued Message:" + message.getContent());
    }

    @GetMapping("/dequeue")
    public ResponseEntity<String> dequeue() {
        Message message = queueService.dequeue();
        if (message == null){
            return  ResponseEntity.ok("Queue is empty");
        }
        processingService.processMessage(message, System.currentTimeMillis());
        return ResponseEntity.ok("Dequeue Message and starting processing...");
    }

    @GetMapping("/queue-size")
    public Long getQueueSize() {
        return queueService.getQueueSize();
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return queueService.getAllMessages();
    }
}
