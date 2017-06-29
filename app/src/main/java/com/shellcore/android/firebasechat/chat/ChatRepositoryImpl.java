package com.shellcore.android.firebasechat.chat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.shellcore.android.firebasechat.chat.event.ChatEvent;
import com.shellcore.android.firebasechat.domain.FirebaseHelper;
import com.shellcore.android.firebasechat.entities.ChatMessage;
import com.shellcore.android.firebasechat.lib.EventBus;
import com.shellcore.android.firebasechat.lib.GreenRobotEventBus;

/**
 * Created by Cesar on 28/06/2017.
 */

class ChatRepositoryImpl implements ChatRepository {

    private String receiver;
    private FirebaseHelper helper;
    private ChildEventListener listener;

    public ChatRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
    }

    @Override
    public void sendMessage(String message) {
        String keySender = helper.getAuthUserEmail()
                .replace(".", "_");
        ChatMessage chatMessage = new ChatMessage(message, keySender);
        DatabaseReference chatReference = helper.getChatsReference(receiver);
        chatReference.push()
                .setValue(chatMessage);
    }

    @Override
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public void destroyChatListener() {
        listener = null;
    }

    @Override
    public void subscribeForChatUpdates() {
        if (listener == null) {
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String sender = chatMessage.getSender();
                    sender = sender.replace("_", ".");

                    String currentUserEmail = helper.getAuthUserEmail();
                    chatMessage.setSentByMe(sender.equals(currentUserEmail));

                    ChatEvent event = new ChatEvent(chatMessage);
                    EventBus eventBus = GreenRobotEventBus.getInstance();
                    eventBus.post(event);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            helper.getChatsReference(receiver)
                    .addChildEventListener(listener);
        }
    }

    @Override
    public void unSubscribeForChatUpdates() {
        if (listener != null) {
            helper.getChatsReference(receiver)
                    .removeEventListener(listener);
        }
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
