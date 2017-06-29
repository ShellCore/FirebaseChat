package com.shellcore.android.firebasechat.login.ui;

/**
 * Created by Cesar on 26/06/2017.
 */

public interface LoginView {

    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignIn();
    void handleSignUp();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
