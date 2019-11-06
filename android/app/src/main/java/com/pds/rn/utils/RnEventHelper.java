package com.pds.rn.utils;

import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.pds.rn.pa.CommonReactPackage;

/**
 * react-native events tools
 *
 * @author hmy
 */
public class RnEventHelper {

    // RNActivity执行onActivityResult
    public static final String EVENT_KEY_ACTIVITY_RESULT = "MEDRN_PAGE_RESULT";
    // RNActivity执行onResume
    public static final String EVENT_KEY_PAGE_SHOW = "MEDRN_PAGE_SHOW";
    // RNActivity执行onStop
    public static final String EVENT_KEY_PAGE_HIDE = "MEDRN_PAGE_HIDE";
    // 通知RN关闭了键盘
    public static final String KEY_BOARD_DID_HIDE = "keyboardDidHide";
    // Fragment可见
    public static final String EVENT_KEY_FRAGMENT_WILL_APPEAR = "RNFragmentWillAppear";
    // Fragment不可见
    public static final String EVENT_KEY_FRAGMENT_WILL_DISAPPEAR = "RNFragmentWillDisappear";
    // 页面回到前台
    public static final String EVENT_KEY_PAGE_WILL_APPEAR = "RNPageWillAppear";

    /**
     * native 发送事件通知react js
     * @param eventName
     * @param params
     */
    public static void sendEvent(String eventName, @Nullable WritableMap params) {
        final ReactApplicationContext reactContext = CommonReactPackage.getReactApplicationContext();
        if (reactContext != null && reactContext.hasActiveCatalystInstance()) {
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }
    }
}
