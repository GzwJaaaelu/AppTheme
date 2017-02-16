package com.google.jaaaelu.gzw.apptheme;


import android.app.Application;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

public class AppThemeApplication extends Application {

    private static AppThemeApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //
        BGASwipeBackManager.getInstance().init(this);
    }

    public static AppThemeApplication getInstance() {
        return sInstance;
    }

}
