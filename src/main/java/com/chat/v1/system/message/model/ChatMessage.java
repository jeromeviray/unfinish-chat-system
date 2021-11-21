package com.chat.v1.system.message.model;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class ChatMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "sender_id")
    private int senderId;
    
    @Column(name = "recipient_id")
    private int recipientId;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "content")
    private String content;
    
//    @Column(name = "created")
//    @DateTimeFormat( pattern = "YYYY-MM-DD")
//    private Date timestamp;
    @Column(name = "message_status")
    private String messageStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        String status = messageStatus.toString();
        System.out.println( status );
        this.messageStatus = status;
    }

//    public Date getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(Date timestamp) {
//        this.timestamp = timestamp;
//    }

//    @Override
//    public String toString() {
//        return "Message{" +
//                "id='" + id + '\'' +
//                ", senderId='" + senderId + '\'' +
//                ", senderName='" + senderName + '\'' +
//                ", content='" + content + '\'' +
//                ", timestamp=" + timestamp +
//                '}';
//    }
}
