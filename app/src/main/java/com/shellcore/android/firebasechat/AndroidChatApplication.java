package com.shellcore.android.firebasechat;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.shellcore.android.firebasechat.lib.GlideImageLoader;
import com.shellcore.android.firebasechat.lib.ImageLoader;

/**
 * Created by Cesar on 27/06/2017.
 */

public class AndroidChatApplication extends Application {

    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        setupImageLoader();
    }

    private void setupFirebase() {
        FirebaseDatabase.getInstance()
                .setPersistenceEnabled(true);
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
