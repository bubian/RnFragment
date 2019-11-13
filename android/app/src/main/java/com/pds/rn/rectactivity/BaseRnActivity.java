package com.pds.rn.rectactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.pds.rn.utils.RnEventHelper;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

/**
 * @author: pengdaosong CreateTime:  2019-11-05 16:23 Email：pengdaosong@medlinker.com Description:
 */

public class BaseRnActivity extends ReactActivity {

    private static final String ROOT_ROUTE = "PdsRnProject";
    /**
     * 页面唯一id
     */
    protected String mPageId;
    protected String mCurrentModuleName;
    protected String mCurrentRouteName;
    // 默认页面
    protected static Object sExtraData;

    protected String getPageId() {
        if (TextUtils.isEmpty(mPageId)) {
            mPageId = String.valueOf(System.currentTimeMillis());
        }
        return mPageId;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RnActivityManger.add(getPageId(), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WritableMap params = new WritableNativeMap();
        params.putString("pageId", getPageId());
        RnEventHelper.sendEvent(RnEventHelper.EVENT_KEY_PAGE_SHOW, params);
    }

    @Override
    protected void onStop() {
        super.onStop();
        WritableMap params = new WritableNativeMap();
        params.putString("pageId", getPageId());
        RnEventHelper.sendEvent(RnEventHelper.EVENT_KEY_PAGE_HIDE, params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RnActivityManger.remove(getPageId());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        onActivityResult(getPageId(), requestCode, resultCode, intent);
    }

    public static void onActivityResult(String pageId, int requestCode, int resultCode,
            Intent intent) {
        if (resultCode == Activity.RESULT_OK) {
            try {
                WritableMap params = new WritableNativeMap();
                params.putString("pageId", pageId);
                params.putInt("code", requestCode);
                RnEventHelper.sendEvent(RnEventHelper.EVENT_KEY_ACTIVITY_RESULT, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected String getMainComponentName() {
        return TextUtils.isEmpty(mCurrentModuleName) ? ROOT_ROUTE : mCurrentModuleName;
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        Object extra = sExtraData;
        Bundle optionBundle = new Bundle();
        optionBundle.putString(RnKey.MODULE_NAME, mCurrentModuleName);
        optionBundle.putString(RnKey.ROUTE_NAME, mCurrentRouteName);
        if (extra instanceof Bundle) {
            optionBundle.putBundle(RnKey.DATA_EXTRA, (Bundle) extra);
        } else if (extra instanceof String) {
            optionBundle.putString(RnKey.DATA_EXTRA, (String) extra);
        }
        optionBundle.putString(RnKey.ROUTE_ID, getPageId());
        RnActivityDelegate delegate = new RnActivityDelegate(this, getMainComponentName()) {
            @Override
            protected ReactRootView createRootView() {
                // npm install --save react-native-gesture-handler 解决手势拦截等问题
                // 需要安装上面的手势库
                 return new RNGestureHandlerEnabledRootView(BaseRnActivity.this);
//                return super.createRootView();
            }
        };
        delegate.setLaunchOptions(optionBundle);
        return delegate;
    }
}
