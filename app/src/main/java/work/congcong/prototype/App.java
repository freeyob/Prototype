package work.congcong.prototype;

import android.app.Application;

import com.facebook.stetho.Stetho;


/**
 * Created by cong on 2017/12/2652.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
