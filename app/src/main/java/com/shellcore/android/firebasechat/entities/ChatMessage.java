package com.shellcore.android.firebasechat.entities;

import com.google.firebase.database.Exclude;

/**
 * Created by Cesar on 28/06/2017.
 */

public class ChatMessage {

    private String message;
    private String sender;
    @Exclude
    private boolean sentByMe;

    public ChatMessage() {
    }

    public ChatMessage(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSentByMe() {
        return sentByMe;
    }

    public void setSentByMe(boolean sentByMe) {
        this.sentByMe = sentByMe;
    }
}
