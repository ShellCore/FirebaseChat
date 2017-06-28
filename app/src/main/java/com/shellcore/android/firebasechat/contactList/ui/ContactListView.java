package com.shellcore.android.firebasechat.contactList.ui;

import com.shellcore.android.firebasechat.entities.User;

/**
 * Created by Cesar on 27/06/2017.
 */

public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
