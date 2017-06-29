package com.shellcore.android.firebasechat.chat.ui;

import com.shellcore.android.firebasechat.entities.ChatMessage;

/**
 * Created by Cesar on 28/06/2017.
 */

public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage message);
}
