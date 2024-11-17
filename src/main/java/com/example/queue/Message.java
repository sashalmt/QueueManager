package com.example.queue;

/**
 * This class represents a Message.
 * It holds the content of the message
 */
public class Message{
    private String content;

    // default constructor
    public Message() {}

    public Message(String content){
        this.content = content;
    }

    // Getter Function
    public String getContent(){
        return content;
    }

    // Setter Function
    public void setContent(String content){
        this.content = content;
    }
}