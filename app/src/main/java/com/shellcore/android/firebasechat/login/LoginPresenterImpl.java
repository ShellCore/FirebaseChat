package com.shellcore.android.firebasechat.login;

import com.shellcore.android.firebasechat.lib.EventBus;
import com.shellcore.android.firebasechat.lib.GreenRobotEventBus;
import com.shellcore.android.firebasechat.login.event.LoginEvent;
import com.shellcore.android.firebasechat.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 26/06/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private EventBus eventBus;

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        interactor = new LoginInteractorImpl();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }
        interactor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }
        interactor.doSignUp(email, password);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }
        interactor.checkAlreadyAuthenticated();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedTorecoverSession();
                break;
        }
    }

    private void onSignInSuccess() {
        if (view != null) {
            view.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess() {
        if (view != null) {
            view.newUserSuccess();
        }
    }

    private void onSignInError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.loginError(error);
        }
    }

    private void onSignUpError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.newUserError(error);
        }
    }

    private void onFailedTorecoverSession() {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
        }
    }
}
