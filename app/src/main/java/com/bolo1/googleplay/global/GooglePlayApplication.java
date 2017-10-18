package com.bolo1.googleplay.global;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public class GooglePlayApplication extends Application {

    private    static Context context;
    private static Handler handler;
    private static int mainThreadId;



    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = Process.myTid();

    }
    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }


}
