package com.pds.pa;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.pds.module.CommonRTCModule;
import com.pds.module.ReactNotificationModule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author pengdaosong
 */
public class CommonReactPackage implements ReactPackage {

    /**
     * cache ReactApplicationContext 用于notify react js
     */
    private static ReactApplicationContext reactApplicationContext;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        reactApplicationContext = reactContext;
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new CommonRTCModule(reactContext));
        modules.add(new ReactNotificationModule(reactContext));
        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(

        );
    }

    public static ReactApplicationContext getReactApplicationContext() {
        return reactApplicationContext;
    }
}
