package com.shellcore.android.firebasechat.contactList;

import com.shellcore.android.firebasechat.contactList.event.ContactListEvent;

/**
 * Created by Cesar on 27/06/2017.
 */

public interface ContactListPresenter {

    void onCreate();
    void onResume();
    void onPause();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);

}
