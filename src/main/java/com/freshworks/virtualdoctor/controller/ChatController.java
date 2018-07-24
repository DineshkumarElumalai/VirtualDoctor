package com.freshworks.virtualdoctor.controller;

import com.freshworks.virtualdoctor.model.ChatMessage;
import com.freshworks.virtualdoctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private SimpMessagingTemplate template;
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }
    @MessageMapping("/{room}/chat.sendMessage")
//    @SendTo("/topic/public")
    public void sendMessage(@DestinationVariable String room, @Payload ChatMessage chatMessage) {
        this.template.convertAndSend("/topic/public/"+room,chatMessage);
    }

    @MessageMapping("/{room}/chat.addUser")
//    @SendTo("/topic/public")
    public void addUser(@DestinationVariable String room,@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("room", room);
        this.template.convertAndSend("/topic/public/"+room,chatMessage);
    }

}
