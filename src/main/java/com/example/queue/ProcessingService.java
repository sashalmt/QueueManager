package com.example.queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ProcessingService{

    @Value("${processing.thread.pool.size:2}")
    private int threadPoolSize;

    private ExecutorService executorService;

    @PostConstruct
    public void init(){
        executorService = new ThreadPoolExecutor(
                threadPoolSize,
                threadPoolSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(threadPoolSize),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public void processMessage(Message message,long dequeueTime){
        executorService.submit(() -> {
            //Simulate message processing
            try {
                System.out.println("Started Processing Message");

                String content = message.getContent();
                //Sleep for 3 seconds
                Thread.sleep(3000);

                //Add a random error
                Random rand = new Random();
                if (rand.nextInt(5) == 0){
                    System.out.println("Message Failed To Process");
                    throw new RuntimeException("Oh no your message failed to process");
                }

                System.out.println("Finished Processing Message " + content + " | " + (System.currentTimeMillis() - dequeueTime) + "ms");
            } catch (Exception e){
                Thread.currentThread().interrupt();
            }
        });

    }
}