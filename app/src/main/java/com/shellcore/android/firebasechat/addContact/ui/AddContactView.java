package com.shellcore.android.firebasechat.addContact.ui;

/**
 * Created by Cesar on 27/06/2017.
 */

public interface AddContactView {

    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();


}
