package com.chat.v1.system.message.service.chatRoom;

import java.util.Optional;

public interface MessageRoomService {
    Optional<String> getChatId(int senderId, int recipientId, boolean createIfNotExist);
}
