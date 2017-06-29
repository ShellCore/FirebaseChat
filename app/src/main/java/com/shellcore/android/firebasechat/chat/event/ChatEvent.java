package com.shellcore.android.firebasechat.chat.event;

import com.shellcore.android.firebasechat.entities.ChatMessage;

/**
 * Created by Cesar on 28/06/2017.
 */

public class ChatEvent {

    ChatMessage message;

    public ChatEvent(ChatMessage message) {
        this.message = message;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
