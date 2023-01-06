package com.jtl.aptdemo;

import android.app.Application;

import com.jtl.router_api.router.Router;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.getInstance().init(this);
    }
}
