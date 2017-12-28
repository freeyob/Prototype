package work.congcong.prototype.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import work.congcong.prototype.R;

/**
 * Created by cong on 2017/12/2652.
 */

public class DialogUtil {
    private static final String TAG = "DialogUtil";
    private static Context sContext;
    public static void install(Context context){
        sContext = context;
    }
    public static void show(String title, String content, final OnConfirmListener listener) {
        AlertDialog dialog = new AlertDialog.Builder(sContext, R.style.Theme_AppCompat_Light_Dialog_Alert_Self).
//                setTitle(title).
                setMessage(content).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG,"setNegativeButton");
                if(listener != null){
                    listener.onCancel();
                }
            }
            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG,"setPositiveButton");
                        if(listener != null){
                            listener.onSure();
                        }
                    }
                }).create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Log.d(TAG,"setOnCancelListener");
                if(listener != null){
                    listener.onCancel();
                }
            }
        });
        dialog.show();
    }

    public interface OnConfirmListener {
        void onSure();

        void onCancel();
    }
}
