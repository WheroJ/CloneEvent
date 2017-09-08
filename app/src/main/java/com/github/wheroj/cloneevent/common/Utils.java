package com.github.wheroj.cloneevent.common;

import android.content.Context;
import android.view.View;

/**
 * Created by shopping on 2017/8/15 16:29.
 *
 * @description
 */

public class Utils {

    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    /**
     * 获取像素尺寸
     * @param size
     * @return
     */
    public static int getPx(int size){
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (density * size + 0.5f);
    }

    public static Context getContext() {
        return mContext;
    }
}
