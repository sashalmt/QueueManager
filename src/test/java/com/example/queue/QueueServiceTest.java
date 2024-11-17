package com.example.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueServiceTest {

    private QueueService queueService;

    @BeforeEach
    public void setUp() {
        queueService = new QueueService();
    }

    @Test
    public void testEnqueue() {
        Message message = new Message("Test Message");
        queueService.enqueue(message);
        assertEquals(1, queueService.getSize());
    }

    @Test
    public void testDequeue() {
        Message message = new Message("Test Message");
        queueService.enqueue(message);
        Message dequeuedMessage = queueService.dequeue();
        assertEquals("Test Message", dequeuedMessage.getContent());
        assertEquals(0, queueService.getSize());
    }

    @Test
    public void testDequeueFromEmptyQueue() {
        Message dequeuedMessage = queueService.dequeue();
        assertNull(dequeuedMessage);
    }
}
