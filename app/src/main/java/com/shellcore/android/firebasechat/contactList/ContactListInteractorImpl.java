package com.shellcore.android.firebasechat.contactList;

/**
 * Created by Cesar on 27/06/2017.
 */

class ContactListInteractorImpl implements ContactListInteractor {

    ContactListRepository repository;

    public ContactListInteractorImpl() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }

    @Override
    public void subscribeForContactEvents() {
        repository.subscribeForContactListUpdates();
    }

    @Override
    public void unsubscribeForContactEvents() {
        repository.unsubscribeForContactListUpdates();
    }

    @Override
    public void destroyContactListListener() {
        repository.destroyContactListListener();
    }
}
