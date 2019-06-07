package com.wsh.thirdlibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import android.view.Gravity;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;

/**
 * Created by zy on 2017/8/21
 */
@SuppressWarnings("unused")
public final class ToastUtils {

    private static final String TAG = ToastUtils.class.getSimpleName();

    /**
     * 默认ID
     */
    public static final int TOAST_ID_DEFAULT = 0;

    /**
     * context
     */
    private static Context sAppContext;

    private static SparseArray<Toast> mToastArray = new SparseArray<Toast>();

    private static Handler sHandler;

    private static final int FREQUENCY = 2000;

    private static String msgMsg;

    private static long lastShowTime;

    private static Toast mToast;

    private ToastUtils() {
    }

    /**
     * 初始化Context
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        if (!isMainThread()) {
            throw new RuntimeException("must call in main thread!");
        }
        sAppContext = context;
        sHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int toastId = msg.arg1;
                String textMsg = (String) msg.obj;
                boolean global = msg.arg2 == 1;
                showToast(toastId, textMsg, global);
            }
        };
        mToast = Toast.makeText(sAppContext, "", Toast.LENGTH_SHORT);
    }

    private static boolean isMainThread() {
        Looper currLooper = Looper.myLooper();
        if (currLooper == null || currLooper != Looper.getMainLooper()) {
            return false;
        }
        return true;
    }

    private static void showToast(int toastId, String msg, boolean global) {
        showToast(toastId, msg, global, -1);
    }

    private static void showToast(int toastId, String msg, boolean global, int gravity) {
        if (StringUtils.isEmpty(msg)) {
            return;
        }

        //添加一个逻辑，不是前台不提醒
//        if (!global && sAppContext != null && !ActivityUtils.isForegroundV2(sAppContext)) {
//            Logger.e(TAG, "showToast & app is background");
//            return;
//        }
        //两秒内，连续相同的msg不显示
        Long time = System.currentTimeMillis() - lastShowTime;
        if (time < FREQUENCY && msg != null && msg.equals(msgMsg)) {
            return;
        }

        if (!isMainThread()) {
            Message msg1 = sHandler.obtainMessage();
            msg1.arg1 = toastId;
            msg1.arg2 = 0;
            if (global) {
                msg1.arg2 = 1;
            }
            msg1.obj = msg;
            msg1.sendToTarget();
            return;
        }

        msgMsg = msg;
        lastShowTime = System.currentTimeMillis();

        int duration = (msg.length() > 50) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        if (toastId > 0) {
            Toast toast = mToastArray.get(toastId);

            if (toast == null) {
                synchronized (ToastUtils.class) {
                    //使用Application Context来防止内存泄露
                    //Context appContext = context.getApplicationContext();
                    toast = Toast.makeText(sAppContext, msg, duration);
                    mToastArray.put(toastId, toast);
                }
            } else {
                //			toast.cancel();
                toast.setText(msg);
                toast.setDuration(duration);

            }
            if (gravity == -1) {//兼容之前，防止出错
                toast.setGravity(Gravity.CENTER, 0, 0);
            } else {
                toast.setGravity(gravity, 0, 0);
            }

            toast.show();
        } else {
            // toastId = 0
            cancel();
            mToast = Toast.makeText(sAppContext, msg, duration);
            if (gravity != -1) {
                mToast.setGravity(gravity, 0, 0);
            }
            mToast.show();
        }
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
            mToast=null;
        }
    }

    /***
     * 展示toast
     * @param context context
     * @param msgResId msgResId
     */
    public static void showDefaultToast(Context context, int msgResId) {
        showToast(TOAST_ID_DEFAULT, context.getString(msgResId), false);
    }

    /***
     * 展示toast
     * @param context context
     * @param msg msg
     */
    public static void showDefaultToast(Context context, String msg) {
        showToast(TOAST_ID_DEFAULT, msg, false);
    }

    public static void showCenterToast(Context context, String msg) {
        showToast(TOAST_ID_DEFAULT, msg, false, Gravity.CENTER);
    }
}
