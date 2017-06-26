package com.shellcore.android.firebasechat.login.event;

/**
 * Created by Cesar on 26/06/2017.
 */

public class LoginEvent {

    public static final int onSignInSuccess = 1;
    public static final int onSignInError = 2;
    public static final int onSignUpSuccess = 3;
    public static final int onSignUpError = 4;
    public static final int onFailedToRecoverSession = 5;

    private int eventType;
    private String errorMessage;

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
}
