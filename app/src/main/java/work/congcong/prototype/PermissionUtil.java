package work.congcong.prototype;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by cong on 2017/12/2852.
 */

public class PermissionUtil {
    private static final String TAG = "PermissionUtil";
    private static final int REQUEST_CODE = 123;

    //判断权限
    public static boolean commonROMPermissionCheck(Context context) {
        Boolean result = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                Class clazz = Settings.class;
                Method canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context.class);
                result = (Boolean) canDrawOverlays.invoke(null, context);
            } catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }
        return result;
    }

    //申请权限
    public static void requestAlertWindowPermission(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data,Activity activity) {
        if (requestCode == REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (commonROMPermissionCheck(activity)) {
                    Log.i(TAG, "onActivityResult granted");
                }else {
                    Log.i(TAG, "onActivityResult deny");

                }
            }
        }
    }
}
