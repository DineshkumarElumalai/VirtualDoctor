package com.freshworks.virtualdoctor.controller;

import com.freshworks.virtualdoctor.model.ChatMessage;
import com.freshworks.virtualdoctor.model.Message;
import com.freshworks.virtualdoctor.repository.ChatRepository;
import com.freshworks.virtualdoctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class ChatController {

    private SimpMessagingTemplate template;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/{room}/{id}/chat.sendMessage")
    public void sendMessage(@DestinationVariable String room,@DestinationVariable String id, @Payload ChatMessage chatMessage) {
        Message message = new Message(chatMessage.getUser(),chatMessage.getId(),chatMessage.getContent(),chatMessage.getCategory());
        chatRepository.save(message);
        this.template.convertAndSend("/topic/public/"+room+"/"+id,chatMessage);
    }

    @MessageMapping("/{room}/{id}/chat.addUser")
    public void addUser(@DestinationVariable String room,@DestinationVariable String id,@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        HashMap<String,Boolean> hm = new HashMap<>();
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUser());
        headerAccessor.getSessionAttributes().put("room", room);
        headerAccessor.getSessionAttributes().put("id", id);
        this.template.convertAndSend("/topic/public/"+room+"/"+id,chatMessage);
    }

}
