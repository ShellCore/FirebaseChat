package com.shellcore.android.firebasechat.addContact;

import com.shellcore.android.firebasechat.addContact.event.AddContactEvent;

/**
 * Created by Cesar on 27/06/2017.
 */

public interface AddContactPresenter {

    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
