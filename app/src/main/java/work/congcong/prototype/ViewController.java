package work.congcong.prototype;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by cong on 2017/12/2752.
 */

public class ViewController {
    private WindowManager mWindowManager;
    private Context mContext;
    View view;

    public ViewController(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        createView();
    }

    public void createView(){
        view = View.inflate(mContext, R.layout.meeting_float_button_view, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startInMeeting(mContext);
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            private int downX;
            private int downY;
            private int tempX;
            private int tempY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //相对于控件左边缘的距离
                        tempX = downX = (int) event.getRawX();
                        tempY = downY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int moveX = (int) event.getRawX();
                        int moveY = (int) event.getRawY();
                        int dx = moveX - downX;
                        int dy = moveY - downY;
                        updatePosition(dx,dy);
                        downX = moveX;
                        downY = moveY;
                        break;
                    case MotionEvent.ACTION_UP:
                        //如果是滑动,事件不在往下传,不显示点击事件
                        return Math.abs(event.getRawX() - tempX) > 3 || Math.abs(event.getRawY() - tempY) > 3;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    public void show(){
        WindowManager.LayoutParams params;
        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        params.format = PixelFormat.TRANSPARENT;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
        }else {
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.x = (int) mContext.getResources().getDimension(R.dimen.dp_32);
        params.y = (int) mContext.getResources().getDimension(R.dimen.dp_118);
        mWindowManager.addView(view,params);
    }

    public void hide(){
        if(mWindowManager !=null && view!= null){
            mWindowManager.removeViewImmediate(view);
            view = null;
        }
    }

    private void updatePosition(int offsetX, int offsetY) {
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams)view.getLayoutParams();
        layoutParams.x = layoutParams.x - offsetX;
        layoutParams.y = layoutParams.y - offsetY;
        mWindowManager.updateViewLayout(view, layoutParams);
    }
}
