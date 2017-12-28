package work.congcong.prototype;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by cong on 2017/12/2752.
 */

public class MeetingService extends Service{
    private static final String TAG = "MeetingService";
    private static final int NOTICE_ID = 111;

    private ViewController mViewController;

    public static void start(Context context){
        Intent intent = new Intent(context,MeetingService.class);
        context.startService(intent);
    }

    public static void stop(Context context){
        Intent intent = new Intent(context,MeetingService.class);
        context.stopService(intent);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mViewController = new ViewController(getApplicationContext());
        show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onCreate");
        return super.onStartCommand(intent, flags, startId);
    }

    private void show(){
        startForeground(NOTICE_ID,NotificationUtil.getMeetingNotification(this));
        mViewController.show();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
        mViewController.hide();
        mViewController = null;
    }
}
