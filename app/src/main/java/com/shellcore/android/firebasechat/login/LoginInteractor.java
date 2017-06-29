package com.shellcore.android.firebasechat.login;

/**
 * Created by Cesar on 26/06/2017.
 */

public interface LoginInteractor {

    void checkAlreadyAuthenticated();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);

}
