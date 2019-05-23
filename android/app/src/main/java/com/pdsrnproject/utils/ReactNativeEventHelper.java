package com.pdsrnproject.utils;

import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.pdsrnproject.pa.CommonReactPackage;

public class ReactNativeEventHelper {

    /**
     * Fragment可见
     */
    public static final String EVENT_KEY_FRAGMENT_WILL_APPEAR = "RNFragmentWillAppear";
    /**
     * Fragment不可见
     */
    public static final String EVENT_KEY_FRAGMENT_WILL_DISAPPEAR = "RNFragmentWillDisappear";

    /**
     * native 发送事件通知react js
     *
     * @param eventName
     * @param params
     */
    public static void setEvent(String eventName, @Nullable WritableMap params) {
        final ReactApplicationContext reactContext = CommonReactPackage.getReactApplicationContext();
        if (reactContext != null && reactContext.hasActiveCatalystInstance()) {
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }
    }
}
