package com.shellcore.android.firebasechat.chat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.shellcore.android.firebasechat.AndroidChatApplication;
import com.shellcore.android.firebasechat.R;
import com.shellcore.android.firebasechat.chat.adapter.ChatAdapter;
import com.shellcore.android.firebasechat.chat.ui.ChatView;
import com.shellcore.android.firebasechat.domain.AvatarHelper;
import com.shellcore.android.firebasechat.entities.ChatMessage;
import com.shellcore.android.firebasechat.lib.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements ChatView {

    // Constants
    public static final String EMAIL_KEY = "email";
    public static final String ONLINE_KEY = "online";

    // Services
    private ChatAdapter adapter;
    private ChatPresenter presenter;

    // Components
    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.txt_user)
    TextView txtUser;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rec_chat)
    RecyclerView recChat;
    @BindView(R.id.edt_message)
    EditText edtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        presenter = new ChatPresenterImpl(this);
        presenter.onCreate();

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        setToolbarData(intent);

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
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.btn_send_message)
    @Override
    public void sendMessage() {
        presenter.sendMessage(edtMessage.getText().toString());
        edtMessage.setText("");
    }

    @Override
    public void onMessageReceived(ChatMessage message) {
        adapter.add(message);
        recChat.scrollToPosition(adapter.getItemCount() - 1);
    }

    private void setToolbarData(Intent i) {
        String recipient = i.getStringExtra(EMAIL_KEY);
        presenter.setChatRecipient(recipient);

        boolean online = i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        AndroidChatApplication app = (AndroidChatApplication) getApplication();
        ImageLoader imageLoader = app.getImageLoader();
        imageLoader.load(imgUser, AvatarHelper.getAvatarUrl(recipient));
    }

    private void setupAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
    }

    private void setupRecyclerView() {
        recChat.setLayoutManager(new LinearLayoutManager(this));
        recChat.setAdapter(adapter);
    }
}
