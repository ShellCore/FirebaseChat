package com.shellcore.android.firebasechat.login;

import com.shellcore.android.firebasechat.login.event.LoginEvent;

/**
 * Created by Cesar on 26/06/2017.
 */

public interface LoginPresenter {

    void onCreate();
    void onDestroy();

    void checkForAuthenticatedUser();
    void onEventMainThread(LoginEvent event);
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
