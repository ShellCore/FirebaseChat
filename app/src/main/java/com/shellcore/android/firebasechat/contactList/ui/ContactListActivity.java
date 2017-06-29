package com.shellcore.android.firebasechat.contactList.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shellcore.android.firebasechat.AndroidChatApplication;
import com.shellcore.android.firebasechat.R;
import com.shellcore.android.firebasechat.addContact.ui.AddContactFragment;
import com.shellcore.android.firebasechat.chat.ChatActivity;
import com.shellcore.android.firebasechat.contactList.ContactListPresenter;
import com.shellcore.android.firebasechat.contactList.ContactListPresenterImpl;
import com.shellcore.android.firebasechat.contactList.adapter.ContactListAdapter;
import com.shellcore.android.firebasechat.entities.User;
import com.shellcore.android.firebasechat.lib.ImageLoader;
import com.shellcore.android.firebasechat.login.ui.LoginActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener {

    // Componentes
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rec_contacts)
    RecyclerView recContacts;

    // Servicios
    private ContactListAdapter adapter;
    private ContactListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        presenter = new ContactListPresenterImpl(this);
        presenter.onCreate();

        toolbar.setSubtitle(presenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);

        setupAdapter();
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                presenter.signOff();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_add)
    public void addContact() {
        AddContactFragment fragment = new AddContactFragment();
        fragment.show(getSupportFragmentManager(), "");
    }

    private void setupAdapter() {
        AndroidChatApplication app = (AndroidChatApplication) getApplication();
        ImageLoader imageLoader = app.getImageLoader();
        adapter = new ContactListAdapter(new ArrayList<User>(), imageLoader, this);
    }

    private void setupRecyclerView() {
        recContacts.setLayoutManager(new LinearLayoutManager(this));
        recContacts.setAdapter(adapter);
    }

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY, user.isOnline());
        startActivity(intent);
    }

    @Override
    public void onItemLong(User user) {
        presenter.removeContact(user.getEmail());
    }
}
