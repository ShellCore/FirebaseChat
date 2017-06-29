package com.shellcore.android.firebasechat.chat;

/**
 * Created by Cesar on 28/06/2017.
 */

class ChatInteractorImpl implements ChatInteractor {

    private ChatRepository repository;

    public ChatInteractorImpl() {
        repository = new ChatRepositoryImpl();
    }

    @Override
    public void sendMessage(String message) {
        repository.sendMessage(message);
    }

    @Override
    public void setRecipient(String recipient) {
        repository.setReceiver(recipient);
    }

    @Override
    public void destroyListener() {
        repository.destroyChatListener();
    }

    @Override
    public void subscribeForChatUpdates() {
        repository.subscribeForChatUpdates();
    }

    @Override
    public void unSubscribeForChatUpdates() {
        repository.unSubscribeForChatUpdates();
    }
}
