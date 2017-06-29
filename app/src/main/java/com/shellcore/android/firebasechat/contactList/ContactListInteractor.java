package com.shellcore.android.firebasechat.contactList;

/**
 * Created by Cesar on 27/06/2017.
 */

public interface ContactListInteractor {

    void removeContact(String email);

    void subscribeForContactEvents();
    void unsubscribeForContactEvents();
    void destroyContactListListener();
}
