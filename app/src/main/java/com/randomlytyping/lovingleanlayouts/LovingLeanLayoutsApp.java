package com.randomlytyping.lovingleanlayouts;

import android.app.Application;

import timber.log.Timber;

public class LovingLeanLayoutsApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
