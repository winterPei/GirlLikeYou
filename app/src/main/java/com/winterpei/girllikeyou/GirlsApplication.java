package com.winterpei.girllikeyou;

import android.app.Application;

/**
 * @author xingyang.pei
 * @date 2017/9/4.
 */

public class GirlsApplication extends Application {

    private static GirlsApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static GirlsApplication getIns() {
        return sInstance;
    }
}
