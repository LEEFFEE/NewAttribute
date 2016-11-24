package cn.huafei.greendao3;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/11/24.
 */

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    //    public static Context getContext() {
    //        return context;
    //    }

}
