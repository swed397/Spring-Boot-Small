package com.example.my.small.springboot.present.proj.controllers;

import com.example.my.small.springboot.present.proj.dtos.chat.ChatMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/chat")
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @MessageMapping("send_message")
    public void messageReceiver(@Payload ChatMessageDto chatMessage){
        logger.info("New message: " + chatMessage);
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getReciever(), "/chat_out/receive_message",
                chatMessage);
    }
}
