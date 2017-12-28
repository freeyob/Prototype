package work.congcong.prototype;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Created by cong on 2017/12/2852.
 */

public class NotificationUtil {
    private static final String CHANEL_MEETING = "CHANEL_MEETING";
    private static final String CHANEL_MEETING_NAME = "会议";
    public static Notification  getMeetingNotification(Context context){
        Intent back = new Intent(context,MainActivity.class);
        back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,back,0);
        NotificationCompat.Builder builder   = new NotificationCompat.Builder(context,CHANEL_MEETING).setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("会议")
                .setContentText("正在会议中，点击进入会议")
                .setContentIntent(pendingIntent);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            builder.setChannelId(CHANEL_MEETING);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(CHANEL_MEETING, CHANEL_MEETING_NAME, NotificationManager.IMPORTANCE_LOW);
            mNotificationManager.createNotificationChannel(mChannel);
        }
        Notification notification = builder.build();
        return notification;
    }
}
