package com.pds.module;

import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


/**
 * @author pengdaosong
 */
public class CommonRTCModule extends ReactContextBaseJavaModule {

    public CommonRTCModule(ReactApplicationContext reactContext) {
        super(reactContext);
        // Add the listener for `onActivityResult`
        reactContext.addActivityEventListener(mActivityEventListener);
        reactContext.addLifecycleEventListener(mLifecycleEventListener);
    }

    private LifecycleEventListener mLifecycleEventListener = new LifecycleEventListener() {
        @Override
        public void onHostResume() {

        }

        @Override
        public void onHostPause() {

        }

        @Override
        public void onHostDestroy() {

        }
    };

    @Override
    public String getName() {
        return "RNNativeModule";
    }


    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode,
                Intent intent) {

        }
    };

    /**
     * 退出rn页面
     */
    @ReactMethod
    public void goBack() {

    }


    /**
     * 屏幕长亮切换
     */
    @ReactMethod
    public void setScreenLongLight(final boolean enable) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Window window = currentActivity.getWindow();
                    if (enable) {
                        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    } else {
                        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    }
                }
            });
        }
    }

}

