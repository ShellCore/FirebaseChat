package com.shellcore.android.firebasechat.lib;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Cesar on 27/06/2017.
 */

public class GlideImageLoader implements ImageLoader {
    private RequestManager requestManager;

    public GlideImageLoader(Context context) {
        requestManager = Glide.with(context);
    }

    @Override
    public void load(CircleImageView imageView, String url) {
        requestManager.load(url)
                .into(imageView);
    }
}
