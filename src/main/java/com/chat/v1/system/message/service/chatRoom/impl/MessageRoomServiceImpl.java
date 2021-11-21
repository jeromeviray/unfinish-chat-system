package com.chat.v1.system.message.service.chatRoom.impl;

import com.chat.v1.system.message.controller.MessageController;
import com.chat.v1.system.message.model.MessageRoom;
import com.chat.v1.system.message.repository.MessageRoomRepository;
import com.chat.v1.system.message.service.chatRoom.MessageRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageRoomServiceImpl implements MessageRoomService {
    Logger logger = LoggerFactory.getLogger(MessageRoomServiceImpl.class);

    @Autowired
    private MessageRoomRepository messageRoomRepository;

    @Override
    public Optional<String> getChatId(int senderId, int recipientId, boolean createIfNotExist) {

//        if(messageRoom.getChatRoomId() != null){
//            return messageRoom.getChatRoomId();
//        }else if(!createIfNotExist){
//            String roomId = String.format("%s_%s", senderId, recipientId);
//
//            MessageRoom senderRecipient = new MessageRoom();
//            senderRecipient.setChatRoomId(roomId);
//            senderRecipient.setSenderId(senderId);
//            senderRecipient.setRecipientId(recipientId);
//
//            MessageRoom recipientSender = new MessageRoom();
//            recipientSender.setChatRoomId(roomId);
//            recipientSender.setRecipientId(recipientId);
//            recipientSender.setSenderId(senderId);
//
//            messageRoomRepository.save(senderRecipient);
//            messageRoomRepository.save(recipientSender);
//        }
        logger.debug("{}", messageRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId));
        return messageRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(MessageRoom::getChatRoomId)
                .or(() -> {
                    if(!createIfNotExist){
                        return null;
                    }
                    String roomId = String.format("%s_%s", senderId, recipientId);
                    MessageRoom roomForSenderAndRecipient = new MessageRoom();
                    roomForSenderAndRecipient.setChatRoomId(roomId);
                    roomForSenderAndRecipient.setSenderId(senderId);
                    roomForSenderAndRecipient.setRecipientId(recipientId);

                    messageRoomRepository.save(roomForSenderAndRecipient);
//                    MessageRoom senderRecipient = new MessageRoom();
//                    senderRecipient.setChatRoomId(roomId);
//                    senderRecipient.setSenderId(senderId);
//                    senderRecipient.setRecipientId(recipientId);
//
//                    MessageRoom recipientSender = new MessageRoom();
//                    recipientSender.setChatRoomId(roomId);
//                    recipientSender.setRecipientId(recipientId);
//                    recipientSender.setSenderId(senderId);
//
//                    messageRoomRepository.save(senderRecipient);
//                    messageRoomRepository.save(recipientSender);

                    return Optional.of(roomId);

                });

    }
}
