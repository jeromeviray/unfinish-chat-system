package com.chat.v1.system.message.service.chatMessage.impl;

import com.chat.v1.system.exception.NotFoundMessageException;
import com.chat.v1.system.message.controller.MessageController;
import com.chat.v1.system.message.model.ChatMessage;
import com.chat.v1.system.message.model.MessageStatus;
import com.chat.v1.system.message.repository.ChatMessageRepository;
import com.chat.v1.system.message.service.chatMessage.ChatMessageService;
import com.chat.v1.system.message.service.chatRoom.MessageRoomService;
import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    Logger logger = LoggerFactory.getLogger(ChatMessageServiceImpl.class);

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private MessageRoomService messageRoomService;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setMessageStatus(MessageStatus.Received);
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public int countNewMessages(int senderId, int recipientId) {
        return chatMessageRepository.countBySenderIdAndRecipientIdAndMessageStatus(senderId, recipientId, "Received");
    }

    @Override
    public List<ChatMessage> findMessages(int senderId, int recipientId) {
        Optional<String> chatRoomId = messageRoomService.getChatId(senderId, recipientId, false);

//        List<ChatMessage> messages = chatRoomId.map(roomId ->
//            chatMessageRepository.findByRoomId(chatRoomId.toString())).orElse(new ArrayList<>());
        List<ChatMessage> messages = chatMessageRepository.findByRoomId(chatRoomId.toString());
        logger.info("messages ", messages +"\n"+ messages.size());
        for(ChatMessage message : messages){
            if(message.getMessageStatus().equalsIgnoreCase("Received")){
                ChatMessage chatMessage = chatMessageRepository.findById(message.getId());
                chatMessage.setMessageStatus(MessageStatus.Delivered);
                chatMessageRepository.save(chatMessage);
            }
        }
        return messages;
    }

    @Override
    public ChatMessage findById(int id) {
        ChatMessage message = chatMessageRepository.findById(id);
        if(message != null){
            message.setMessageStatus(MessageStatus.Delivered);
            chatMessageRepository.save(message);
            return message;
        }else {
            throw new NotFoundMessageException("No such message found: id "+id);
        }

//        return chatMessageRepository.findById(id).map(message ->{
//           message.setMessageStatus(MessageStatus.Delivered);
//           chatMessageRepository.save(message);
//        }).orElseThrow(() -> new ResourceNotFoundException("can't find message (" + id + ")")););
    }

    @Override
    public void updateMessageStatus(int senderId, int recipientId) {
//        chatMessageRepository.updateMessagesStatus(senderId, recipientId, MessageStatus.Delivered);
        ChatMessage message = chatMessageRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        message.setMessageStatus(MessageStatus.Delivered);
        chatMessageRepository.save(message);
    }
}
