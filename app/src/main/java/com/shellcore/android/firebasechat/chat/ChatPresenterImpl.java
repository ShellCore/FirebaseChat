package com.shellcore.android.firebasechat.chat;

import com.shellcore.android.firebasechat.chat.event.ChatEvent;
import com.shellcore.android.firebasechat.chat.ui.ChatView;
import com.shellcore.android.firebasechat.entities.ChatMessage;
import com.shellcore.android.firebasechat.entities.User;
import com.shellcore.android.firebasechat.lib.EventBus;
import com.shellcore.android.firebasechat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 28/06/2017.
 */

class ChatPresenterImpl implements ChatPresenter {

    private EventBus eventBus;
    private ChatView view;
    private ChatInteractor interactor;
    private ChatSessionInteractor sessionInteractor;

    public ChatPresenterImpl(ChatView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        interactor = new ChatInteractorImpl();
        sessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        interactor.unSubscribeForChatUpdates();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        interactor.subscribeForChatUpdates();
        sessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        interactor.destroyListener();
        view = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        interactor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String message) {
        interactor.sendMessage(message);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (view != null) {
            ChatMessage chatMessage = event.getMessage();
            view.onMessageReceived(chatMessage);
        }
    }
}
