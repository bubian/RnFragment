package com.pds.rn.module;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

/**
 * @author pengdaosong
 */
public class ReactNotificationModule extends ReactContextBaseJavaModule {

    private static final String MODULE_NAME = "RNNotifcationModule";
    private static final String TAG = ReactNotificationModule.class.getSimpleName();

    public ReactNotificationModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }


    /**
     * @param nId 通知类型id
     * @param params 事件参数可选
     */
    @ReactMethod
    public void postNotification(int nId, ReadableMap params) {

    }
}
