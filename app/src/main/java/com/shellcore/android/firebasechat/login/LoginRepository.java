package com.shellcore.android.firebasechat.login;

/**
 * Created by Cesar on 26/06/2017.
 */

public interface LoginRepository {

    void checkAlreadyAuthenticated();
    void signIn(String email, String password);
    void signUp(String email, String password);
}
