package com.medlinker.base.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.EditText;

import com.heaven7.core.util.Logger;
import com.heaven7.core.util.SPHelper;
import com.medlinker.base.BaseApplication;

import java.util.List;
import java.util.UUID;

public class DeviceUtil {

    private static int STATUSBAR_HEIGHT = 0;
    private static DisplayMetrics mDisplayMetrics;

    public static void getWindowRect(Context context, Rect outRect) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        outRect.set(0, 0, dm.widthPixels, dm.heightPixels);
    }

    public static DisplayMetrics getDisplayMetrics() {
        if (null == mDisplayMetrics) {
            mDisplayMetrics = BaseApplication.getApplication().getResources().getDisplayMetrics();
        }
        return mDisplayMetrics;
    }

    /**
     * 屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }


    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(@NonNull Activity activity) {
        if (STATUSBAR_HEIGHT > 0) {
            return STATUSBAR_HEIGHT;
        }
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusHeight = frame.top;
        if (statusHeight <= 0) {
            // 获得状态栏高度
            final Resources resources = activity.getResources();
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            statusHeight = resources.getDimensionPixelSize(resourceId);
        }
        STATUSBAR_HEIGHT = statusHeight;
        return statusHeight;
    }


    /**
     * 设置输入框光标位置
     *
     * @param editText EditText
     */
    public static void setTextCursor(EditText editText) {
        CharSequence text = editText.getText();
        if (text != null) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }

    /**
     * 记录上次点击时间
     */
    private static long mLastClickTime;

    /**
     * 判断是否快速点击
     *
     * @return
     */
    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        long distTime = time - mLastClickTime;
        if (0 < distTime && distTime < 600) {
            return true;
        }
        mLastClickTime = time;
        return false;
    }

    /**
     * 获取设备堆内存大小
     *
     * @param context 应用上下文
     * @return
     */
    public static int getMemoryClass(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        return manager.getMemoryClass();
    }


    /**
     * 删除快捷方式
     */
    public static void deleteShortCut(Context activity, String shortcutName) {
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        //快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        /**改成以下方式能够成功删除，估计是删除和创建需要对应才能找到快捷方式并成功删除**/
        Intent intent = new Intent();
        intent.setClass(activity, activity.getClass());
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        activity.sendBroadcast(shortcut);
    }


    /**
     * 复制文本到剪贴板
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void copyText(Context context, String text) {
        if (null == context || TextUtils.isEmpty(text)) {
            return;
        }
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText(text, text));
    }

    /**
     * check if sdcard exist
     *
     * @return
     */
    public static boolean isSdcardExist() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * deviceID的组成为：渠道标志+识别符来源标志+hash后的终端识别符
     * <p>
     * 渠道标志为：
     * 1，andriod（a）
     * <p>
     * 识别符来源标志：
     * 1， wifi mac地址（wifi）；
     * 2， IMEI（imei）；
     * 3， 序列号（sn）；
     * 4， id：随机码。若前面的都取不到时，则随机生成一个随机码，需要缓存。
     */
    public static String getDeviceId(Context context) {

        StringBuilder deviceId = new StringBuilder();
        try {
            //IMEI（imei）//Need read phone state PERMISSION
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!TextUtils.isEmpty(imei)) {
                deviceId.append("imei");
                deviceId.append(imei);
                Logger.d("Track", "getDeviceId_imei", deviceId.toString());
                return deviceId.toString();
            }
            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!TextUtils.isEmpty(sn)) {
                deviceId.append("sn");
                deviceId.append(sn);
                Logger.d("Track", "getDeviceId_sn", deviceId.toString());
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            final String aid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if (!TextUtils.isEmpty(aid)) {
                Logger.d("Track", "getDeviceId_ANDROID_ID", aid);
                return aid;
            }

            //wifi mac地址
            WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!TextUtils.isEmpty(wifiMac)) {
                deviceId.append("wifi");
                deviceId.append(wifiMac);
                Logger.d("Track", "getDeviceId_mac", deviceId.toString());
                return deviceId.toString();
            }
            //如果上面都没有， 则生成一个id：随机码
            String uuid = getUUID(context);
            if (!TextUtils.isEmpty(uuid)) {
                deviceId.append("uuid");
                deviceId.append(uuid);
                Logger.d("Track", "getDeviceId_RANDOM", deviceId.toString());
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append("uuid").append(getUUID(context));
        }
        Logger.d("Track", "getDeviceId_RANDOM", deviceId.toString());
        return deviceId.toString();
    }


    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        String uuid = SPHelper.getString(context, "sysCacheMap_uuid");
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SPHelper.putString(context, "sysCacheMap_uuid", uuid);
        }
        Logger.d("Track", "getUUID", uuid);
        return uuid;
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
