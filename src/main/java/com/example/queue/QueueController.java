package com.example.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/queue")
public class QueueController{

    @Autowired
    private QueueService queueService;

    @Autowired
    private ProcessingService processingService;

    // POST /queue/enqueue
    @PostMapping("/enqueue")
    public ResponseEntity<String> enqueue(@RequestBody Message message){
        queueService.enqueue(message);
        return ResponseEntity.ok("Message enqueued successfully :)");
    }

    // GET /queue/dequeue
    @GetMapping("/dequeue")
    public ResponseEntity<String> dequeue(){
        Message message = queueService.dequeue();

        if(message == null){
            return ResponseEntity.ok("Queue is empty");
        }

        processingService.processMessage(message);
        return ResponseEntity.ok("Message dequeue successfully and processing...");

    }

    // GET /queue/queue-size
    @GetMapping("/queue-size")
    public ResponseEntity<Integer> getSize() {
        int size = queueService.getSize();
        return ResponseEntity.ok(size);
    }

}