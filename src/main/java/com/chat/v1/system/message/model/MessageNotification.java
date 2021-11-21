package com.chat.v1.system.message.model;

import java.io.Serializable;

public class MessageNotification implements Serializable {

    private int notificationId;
    private int senderId;
    private String senderName;

    public MessageNotification() {
    }

    public MessageNotification(int senderId, String senderName) {
        this.senderId = senderId;
        this.senderName = senderName;
    }

    public MessageNotification(int notificationId, int senderId, String senderName) {
        this.notificationId = notificationId;
        this.senderId = senderId;
        this.senderName = senderName;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
