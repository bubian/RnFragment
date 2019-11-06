package com.pds.rn.utils;

import android.app.Activity;
import android.os.Build;
import com.pds.base.utils.ImmersiveModeUtil;

/**
 * @author: pengdaosong
 * CreateTime:  2019-11-05 16:36
 * Email：pengdaosong@medlinker.com
 * ®Description:
 */
public class ConfigHelper {
    public static void configImmersiveMode(Activity activity) {
        // 成功设置为darkmode后才支持透明状态栏模式
        // RN页面特殊处理： >= 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ImmersiveModeUtil.setStatusBarTransparent(activity);
        }
    }
}
