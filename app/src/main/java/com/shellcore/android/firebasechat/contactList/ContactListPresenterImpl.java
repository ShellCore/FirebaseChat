package com.shellcore.android.firebasechat.contactList;

import com.shellcore.android.firebasechat.contactList.event.ContactListEvent;
import com.shellcore.android.firebasechat.contactList.ui.ContactListView;
import com.shellcore.android.firebasechat.entities.User;
import com.shellcore.android.firebasechat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 27/06/2017.
 */

public class ContactListPresenterImpl implements ContactListPresenter {

    private com.shellcore.android.firebasechat.lib.EventBus eventBus;
    private ContactListView view;
    private ContactListSessionInteractor sessionInteractor;
    private ContactListInteractor interactor;

    public ContactListPresenterImpl(ContactListView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        sessionInteractor = new ContactListSessionInteractorImpl();
        interactor = new ContactListInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {
        sessionInteractor.changeConnectionStatus(User.ONLINE);
        interactor.subscribeForContactEvents();
    }

    @Override
    public void onPause() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        interactor.unsubscribeForContactEvents();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        interactor.destroyContactListListener();
        view = null;
    }

    @Override
    public void signOff() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        interactor.destroyContactListListener();
        interactor.unsubscribeForContactEvents();
        sessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        interactor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    private void onContactAdded(User user) {
        if (view != null) {
            view.onContactAdded(user);
        }
    }

    private void onContactChanged(User user) {
        if (view != null) {
            view.onContactChanged(user);
        }
    }

    private void onContactRemoved(User user) {
        if (view != null) {
            view.onContactRemoved(user);
        }
    }
}
