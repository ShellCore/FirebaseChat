package com.shellcore.android.firebasechat.contactList;

/**
 * Created by Cesar on 27/06/2017.
 */

public interface ContactListSessionInteractor {

    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
    void signOff();

}
