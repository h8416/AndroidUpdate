package cn.dcoder.audoupdatedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Henry Chang on 2016/9/27.
 */
public class App extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(mContext);
    }
}
