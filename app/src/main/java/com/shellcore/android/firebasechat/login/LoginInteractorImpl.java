package com.shellcore.android.firebasechat.login;

/**
 * Created by Cesar on 26/06/2017.
 */

class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository repository;

    public LoginInteractorImpl() {
        this.repository = new LoginRepositoryImpl();
    }

    @Override
    public void doSignIn(String email, String password) {
        repository.signIn(email, password);
    }

    @Override
    public void doSignUp(String email, String password) {
        repository.signUp(email, password);
    }

    @Override
    public void checkAlreadyAuthenticated() {
        repository.checkAlreadyAuthenticated();
    }
}
