package com.chat.v1.system.message.controller;

import com.chat.v1.system.message.model.ChatMessage;
import com.chat.v1.system.message.model.MessageNotification;
import com.chat.v1.system.message.service.chatMessage.ChatMessageService;
import com.chat.v1.system.message.service.chatRoom.MessageRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private MessageRoomService messageRoomService;

    Logger logger = LoggerFactory.getLogger(MessageController.class);


    @MessageMapping( value = "/chat" )
    public void privateMessage(@Payload ChatMessage message){
        logger.info("{}", message);
        Optional<String> roomId = messageRoomService.getChatId(message.getSenderId(), message.getRecipientId(), true);
        message.setRoomId(String.valueOf(roomId));
        ChatMessage chatMessage = chatMessageService.save(message);

        simpMessagingTemplate.convertAndSendToUser(chatMessage.getRecipientName(),
                                        "/private/messages",
                                                    new MessageNotification(chatMessage.getId(), chatMessage.getSenderId(), chatMessage.getSenderName()));
        logger.debug("{}"+simpMessagingTemplate);
    }
    @GetMapping(value = "/message/{senderId}/{recipientId}/count")
    public ResponseEntity<Integer> countNewMessage(@PathVariable int senderId,
                                          @PathVariable int recipientId){
        return ResponseEntity.ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping(value = "message/{senderId}/{recipientId}")
    public ResponseEntity<?> findMessages(@PathVariable int senderId,
                                          @PathVariable int recipientId){
        logger.info("{}", chatMessageService.findMessages(senderId, recipientId));
        return ResponseEntity.ok(chatMessageService.findMessages(senderId, recipientId));
    }
    @GetMapping( value = "message/{id}")
    public ResponseEntity<?> findMessage(@PathVariable int id){
        return ResponseEntity.ok(chatMessageService.findById(id));
    }
}
