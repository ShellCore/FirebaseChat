package com.shellcore.android.firebasechat.contactList.ui;

import com.shellcore.android.firebasechat.entities.User;

/**
 * Created by Cesar on 27/06/2017.
 */

public interface OnItemClickListener {

    void onItemClick(User user);
    void onItemLong(User user);
}

