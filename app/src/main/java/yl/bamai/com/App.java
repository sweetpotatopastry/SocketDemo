package yl.bamai.com;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/7/12.
 */
public class App extends Application {
    static  Context context;
    @Override
    public void onCreate() {
        this.context = getApplicationContext();
        super.onCreate();
    }

    public static Context getApp()
    {
        return context;
    }
}
