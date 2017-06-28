package com.shellcore.android.firebasechat.addContact;

/**
 * Created by Cesar on 27/06/2017.
 */

class AddContactInteractorImpl implements AddContactInteractor {

    AddContactRepository repository;

    public AddContactInteractorImpl() {
        repository = new AddContactRepositoryImpl();
    }

    @Override
    public void addContact(String email) {
        repository.addContact(email);
    }
}
