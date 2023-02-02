package com.example.my.small.springboot.present.proj.dtos.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    private String sender;
    private String reciever;
    private String message;
    private LocalDateTime localDateTime;

    public ChatMessageDto(LocalDateTime localDateTime) {
        this.localDateTime = LocalDateTime.now();
    }

    public ChatMessageDto(String sender, String reciever, String message) {
        this();
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.localDateTime = localDateTime;
    }
}
