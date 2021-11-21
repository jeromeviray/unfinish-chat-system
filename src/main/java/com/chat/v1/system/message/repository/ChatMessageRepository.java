package com.chat.v1.system.message.repository;


import com.chat.v1.system.message.model.ChatMessage;
import com.chat.v1.system.message.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

    ChatMessage findById(int id);
    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> findByRoomId(String id);
    int countBySenderIdAndRecipientIdAndMessageStatus(int senderId, int recipientId, String status);
    ChatMessage findBySenderIdAndRecipientId(int senderId, int recipientId);
//    @Modifying
//    @Query(value = "update message m set m.message_status = :status where m.senderId = :senderId and m.recipientId = :recipientId", nativeQuery = true)
//    void updateMessagesStatus(@Param(value = "senderId") int senderId,
//                              @Param(value = "recipientId") int recipientId,
//                              @Param(value = "status") MessageStatus status);
}
