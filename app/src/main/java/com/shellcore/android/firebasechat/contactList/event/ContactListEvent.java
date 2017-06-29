package com.shellcore.android.firebasechat.contactList.event;

import com.shellcore.android.firebasechat.entities.User;

/**
 * Created by Cesar on 27/06/2017.
 */

public class ContactListEvent {

    public static final int onContactRemoved = 1;
    public static final int onContactChanged = 2;
    public static final int onContactAdded = 3;

    private int eventType;
    private String errorMessage;
    private User user;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
