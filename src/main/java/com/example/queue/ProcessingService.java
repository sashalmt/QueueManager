package com.example.queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
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
                new ThreadPoolExecutor.AbortPolicy() // Rejects tasks when the queue is full
        );
    }

    public void processMessage(Message message){
        executorService.submit(() -> {
            //Simulate message processing
            try {
                System.out.println("Started Processing Message");
                long curTime = System.currentTimeMillis();
                String content = message.getContent();

                //Sleep for a random amount of time to simulated processing
                Random rand = new Random();
                Thread.sleep(1000*(rand.nextInt(5)+1));

                //Add a random error
                if (0 == rand.nextInt(3)){
                    throw new RuntimeException("Oh no your message failed to process");
                }

                System.out.println("Finished Processing Message " + content + " | " + (System.currentTimeMillis() - curTime) + "ms");
            } catch (Exception e){
                Thread.currentThread().interrupt();
            }
        });

    }
}