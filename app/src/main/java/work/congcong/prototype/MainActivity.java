package work.congcong.prototype;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.congcong.prototype.ui.DialogUtil;

public class MainActivity extends Activity {
    @BindView(R.id.show) TextView show;
    public static boolean activityIsRuning;
    public static MainActivity activity;

    public static void start(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void startInMeeting(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        activityIsRuning = true;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DialogUtil.install(this);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(PermissionUtil.permissionCheck(MainActivity.this)){
                    Toast.makeText(MainActivity.this,"已经有权限了",Toast.LENGTH_SHORT).show();
               }else {
                    PermissionUtil.requestAlertWindowPermission(MainActivity.this);
               }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        MeetingService.start(MainActivity.this);
    }

    @Override
    protected void onDestroy() {
        activity = null;
        activityIsRuning = false;
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MeetingService.stop(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PermissionUtil.onActivityResult(requestCode,resultCode,data,this);
    }

    private void showDialog(){
        DialogUtil.show("提示", "退出应用", new DialogUtil.OnConfirmListener() {
            @Override
            public void onSure() {
                Toast.makeText(getBaseContext(),"确认",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseContext(),"取消",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
