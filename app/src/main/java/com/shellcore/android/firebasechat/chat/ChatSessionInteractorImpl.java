package com.shellcore.android.firebasechat.chat;

/**
 * Created by Cesar on 28/06/2017.
 */

class ChatSessionInteractorImpl implements ChatSessionInteractor {

    private ChatRepository repository;

    public ChatSessionInteractorImpl() {
        repository = new ChatRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
