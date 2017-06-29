package com.shellcore.android.firebasechat.chat;

/**
 * Created by Cesar on 28/06/2017.
 */

public interface ChatRepository {

    void sendMessage(String message);
    void setReceiver(String receiver);

    void destroyChatListener();
    void subscribeForChatUpdates();
    void unSubscribeForChatUpdates();

    void changeConnectionStatus(boolean online);
}
