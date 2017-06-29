package com.shellcore.android.firebasechat.contactList;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.shellcore.android.firebasechat.contactList.event.ContactListEvent;
import com.shellcore.android.firebasechat.domain.FirebaseHelper;
import com.shellcore.android.firebasechat.entities.User;
import com.shellcore.android.firebasechat.lib.EventBus;
import com.shellcore.android.firebasechat.lib.GreenRobotEventBus;

/**
 * Created by Cesar on 27/06/2017.
 */

class ContactListRepositoryImpl implements ContactListRepository {

    private FirebaseHelper helper;
    private ChildEventListener listener;

    public ContactListRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
    }


    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail, email).removeValue();
        helper.getOneContactReference(email, currentUserEmail).removeValue();
    }

    @Override
    public void destroyContactListListener() {
        listener = null;
    }

    @Override
    public void subscribeForContactListUpdates() {
        if (listener == null) {
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_" ,".");
                    boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactAdded, user);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_", ".");
                    boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactChanged, user);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String email = dataSnapshot.getKey();
                    email = email.replace(".", "_");
                    boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactRemoved, user);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
        }

        helper.getMyContactsReference()
                .addChildEventListener(listener);
    }

    @Override
    public void unsubscribeForContactListUpdates() {
        if (listener != null) {
            helper.getMyContactsReference().removeEventListener(listener);
        }
    }

    @Override
    public void changeUserConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }

    private void postEvent(int type, User user) {
        ContactListEvent event = new ContactListEvent();
        event.setEventType(type);
        event.setUser(user);

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }
}
