package com.pds.rn.rectactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.pds.base.MainApplication;
import com.pds.rn.utils.ConfigHelper;
import com.pds.rn.utils.RnEventHelper;

/**
 * @author pengdaosong
 */
public class RnActivity extends BaseRnActivity {
    private boolean mIsAppearing = false;
    private static final int USELESS_REQUEST_CODE = -100;

    private static void launchReactActivity(String module,String route,Bundle bundle){
        launchReactActivity(MainApplication.getApplication(),module,route,bundle,USELESS_REQUEST_CODE);
    }
    private static void launchReactActivity(Context context,String module,String route,Bundle bundle){
        launchReactActivity(context,module,route,bundle,USELESS_REQUEST_CODE);
    }
    public static void launchReactActivity(Context context, String module, String route,
            Object extra, int requestCode){
        if (extra != null) {
            sExtraData = extra;
        }
        if (context == null || ((context instanceof Activity) && ((Activity) context).isFinishing())) {
            return;
        }
        if (hasPermission()) {
            Intent intent = new Intent(context, RnActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            Bundle optionBundle = new Bundle();
            optionBundle.putString(RnKey.MODULE_NAME, module);
            optionBundle.putString(RnKey.ROUTE_NAME, route);
            if (extra instanceof Bundle) {
                optionBundle.putBundle(RnKey.DATA_EXTRA, (Bundle) extra);
            } else if (extra instanceof String) {
                optionBundle.putString(RnKey.DATA_EXTRA, (String) extra);
            }
            intent.putExtras(optionBundle);
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, requestCode);
            } else {
                context.startActivity(intent);
            }
        }
    }

    private static boolean hasPermission() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if (TextUtils.isEmpty(mCurrentRouteName)) {
            //被回收时恢复，关闭页面
            // finish();
            // return;
        // }
        ConfigHelper.configImmersiveMode(this);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
            extras.putString(RnKey.MODULE_NAME,mCurrentModuleName);
        }
        sExtraData = extras;
        mCurrentModuleName = extras.getString(RnKey.MODULE_NAME);
        mCurrentRouteName = extras.getString(RnKey.ROUTE_NAME);
        // GrowingIO统计
        // GrowingIO.getInstance().setPageName(this, String.format("RN_%s_%s", mCurrentModuleName, mCurrentRouteName));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mIsAppearing = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //如果恢复状态，通知RN界面回到前台，不放在onRestart是避免微信等分享 “留在微信” 回调onRestart
        if (mIsAppearing) {
            WritableMap params = new WritableNativeMap();
            params.putString(RnKey.MODULE_NAME, mCurrentModuleName);
            params.putString(RnKey.ROUTE_NAME, mCurrentRouteName);
            RnEventHelper.sendEvent(RnEventHelper.EVENT_KEY_PAGE_WILL_APPEAR, params);
            mIsAppearing = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
