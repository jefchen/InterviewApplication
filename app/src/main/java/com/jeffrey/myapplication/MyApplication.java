package com.jeffrey.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();
    public static final String ACTIVITY = "Activity";

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Log.d(ACTIVITY, "onActivityCreated()");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(ACTIVITY, "onActivityStarted()");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(ACTIVITY, "onActivityResumed()");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(ACTIVITY, "onActivityPaused()");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(ACTIVITY, "onActivityStopped()");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                Log.d(ACTIVITY, "onActivitySaveInstanceState()");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(ACTIVITY, "onActivityDestroyed()");
            }
        });
    }

    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate()");
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged()");
    }

    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory()");
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory()");
    }
}
