package com.shellcore.android.firebasechat.chat;

/**
 * Created by Cesar on 28/06/2017.
 */

public interface ChatInteractor {

    void sendMessage(String message);
    void setRecipient(String recipient);

    void destroyListener();
    void subscribeForChatUpdates();
    void unSubscribeForChatUpdates();
}
