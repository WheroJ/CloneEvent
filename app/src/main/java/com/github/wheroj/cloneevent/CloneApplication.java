package com.github.wheroj.cloneevent;

import android.app.Application;

import com.github.wheroj.cloneevent.common.Utils;

/**
 * Created by shopping on 2017/8/15 16:32.
 *
 * @description
 */

public class CloneApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
