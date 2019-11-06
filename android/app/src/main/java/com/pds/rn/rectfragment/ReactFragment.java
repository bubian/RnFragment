package com.pds.rectfragment;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.pds.utils.FragmentRNCollector;
import com.pds.utils.IntentHelper;
import com.pds.utils.ReactNativeEventHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/**
 * Author: pengdaosong.
 * <p>
 * CreateTime:  2018/11/9 4:10 PM
 * <p>
 * Email：pengdaosong@medlinker.com.
 * <p>
 * Description:
 */
public class ReactFragment extends BaseReactFragment implements
        DefaultHardwareBackBtnHandler {


    private static final String TAG = "ReactFragment";

    private static final String SCHEME = "sch:";
    private Map<String, String> mParams = new HashMap<>();

    private String mCurModuleName;
    private String mCurPageName;

    private static final String MODULE_NAME = "moduleName";
    private static final String ROUTE_NAME = "routeName";
    private static final String EXTRA = "extra";
    private static final String SPLIT = "/";
    private String mExtra;
    private boolean mIsStoped;
    private boolean mIsVisibleToUser;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RnModuleName {

    }


    @Nullable
    @Override
    protected String getMainComponentName() {
        return mCurModuleName;
    }

    private String getCurrentModuleRouterName() {
        return String.format("%s-%s", mCurModuleName, mCurPageName);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (null == bundle) {
            return;
        }
        String url = IntentHelper.getExtraStr(bundle);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (url.trim().startsWith(SPLIT)) {
            url = SCHEME.concat(url);
        }
        Uri uri = Uri.parse(url);
        if (uri != null) {
            try {
                for (String key : uri.getQueryParameterNames()) {
                    this.mParams.put(key, uri.getQueryParameter(key));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (null != mParams && mParams.size() > 0) {
            mCurModuleName = mParams.get(MODULE_NAME);
            mCurPageName = mParams.get(ROUTE_NAME);
            mExtra = mParams.get(EXTRA);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        FragmentRNCollector.onVisible(getCurrentModuleRouterName(), mIsVisibleToUser && !mIsStoped);
        createWritableMap(isVisibleToUser ? ReactNativeEventHelper.EVENT_KEY_FRAGMENT_WILL_APPEAR
                : ReactNativeEventHelper.EVENT_KEY_FRAGMENT_WILL_DISAPPEAR);
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsStoped = false;
        FragmentRNCollector.onVisible(getCurrentModuleRouterName(), mIsVisibleToUser && !mIsStoped);
    }

    @Override
    public void onStop() {
        super.onStop();
        mIsStoped = true;
        FragmentRNCollector.onVisible(getCurrentModuleRouterName(), mIsVisibleToUser && !mIsStoped);
    }

    private void createWritableMap(String eventName) {
        WritableMap params = new WritableNativeMap();
        params.putString(MODULE_NAME, mCurModuleName);
        params.putString(ROUTE_NAME, mCurPageName);
        ReactNativeEventHelper.setEvent(eventName, params);
    }

    @Override
    protected BaseReactFragmentDelegate createReactFragmentDelegate() {
        ReactFragmentDelegate delegate = new ReactFragmentDelegate(this,
                getMainComponentName());
        Bundle optionBundle = new Bundle();
        optionBundle.putString(MODULE_NAME, mCurModuleName);
        optionBundle.putString(ROUTE_NAME, mCurPageName);
        optionBundle.putString(EXTRA, mExtra);
        delegate.setLaunchOptions(optionBundle);
        return delegate;
    }

    @Override
    public void invokeDefaultOnBackPressed() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentRNCollector.remove(getCurrentModuleRouterName());
    }
}
