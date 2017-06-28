package com.shellcore.android.firebasechat.contactList;

/**
 * Created by Cesar on 27/06/2017.
 */

class ContactListSessionInteractorImpl implements ContactListSessionInteractor {

    ContactListRepository repository;

    public ContactListSessionInteractorImpl() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeUserConnectionStatus(online);
    }

    @Override
    public void signOff() {
        repository.signOff();
    }
}
