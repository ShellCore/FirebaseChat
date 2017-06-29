package com.shellcore.android.firebasechat.lib;

/**
 * Created by Cesar on 27/06/2017.
 */

public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
