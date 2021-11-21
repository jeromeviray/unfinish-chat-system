package com.chat.v1.system.message.repository;

import com.chat.v1.system.message.model.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Integer> {
    Optional<MessageRoom> findBySenderIdAndRecipientId(int senderId, int recipientId);
}
