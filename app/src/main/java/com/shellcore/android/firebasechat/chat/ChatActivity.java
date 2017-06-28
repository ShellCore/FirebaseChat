package com.shellcore.android.firebasechat.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shellcore.android.firebasechat.R;

public class ChatActivity extends AppCompatActivity {

    public static final String EMAIL_KEY = "email";
    public static final String ONLINE_KEY = "online";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
}
