package com.chat.v1.system.message.service.chatMessage;

import com.chat.v1.system.message.model.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
    int countNewMessages(int senderId, int recipientId);
    List<ChatMessage> findMessages(int senderId, int recipientId);
    ChatMessage findById(int id);
    void updateMessageStatus(int senderId, int recipientId);

}
