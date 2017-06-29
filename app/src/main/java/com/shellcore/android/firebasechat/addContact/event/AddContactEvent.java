package com.shellcore.android.firebasechat.addContact.event;

/**
 * Created by Cesar on 27/06/2017.
 */

public class AddContactEvent {

    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
