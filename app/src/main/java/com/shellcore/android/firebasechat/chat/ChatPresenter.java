package com.shellcore.android.firebasechat.chat;

import com.shellcore.android.firebasechat.chat.event.ChatEvent;

/**
 * Created by Cesar on 28/06/2017.
 */

public interface ChatPresenter {

    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();

    void setChatRecipient(String recipient);

    void sendMessage(String message);
    void onEventMainThread(ChatEvent event);
}
