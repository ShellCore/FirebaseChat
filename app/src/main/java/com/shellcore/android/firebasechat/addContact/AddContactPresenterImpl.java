package com.shellcore.android.firebasechat.addContact;

import com.shellcore.android.firebasechat.addContact.event.AddContactEvent;
import com.shellcore.android.firebasechat.addContact.ui.AddContactView;
import com.shellcore.android.firebasechat.lib.EventBus;
import com.shellcore.android.firebasechat.lib.GreenRobotEventBus;


import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 27/06/2017.
 */

public class AddContactPresenterImpl implements AddContactPresenter {

    private EventBus eventBus;
    private AddContactView view;
    private AddContactInteractor interactor;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        interactor = new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if (view != null) {
            view.hideInput();
            view.showProgress();
        }
        interactor.addContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();

            if (event.isError()) {
                view.contactNotAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}
