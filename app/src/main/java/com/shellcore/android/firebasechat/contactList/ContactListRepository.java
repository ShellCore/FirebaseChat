package com.shellcore.android.firebasechat.contactList;

/**
 * Created by Cesar on 27/06/2017.
 */

public interface ContactListRepository {

    void signOff();
    String getCurrentEmail();

    void removeContact(String email);
    void destroyContactListListener();
    void subscribeForContactListUpdates();
    void unsubscribeForContactListUpdates();

    void changeUserConnectionStatus(boolean online);
}
