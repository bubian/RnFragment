package com.pdsrnproject.rectfragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import javax.annotation.Nullable;

/**
 * Author: pengdaosong.
 * <p>
 * CreateTime:  2018/11/9 4:34 PM
 * <p>
 * Email：pengdaosong@medlinker.com.
 * <p>
 * Description:
 */
public abstract class BaseReactFragment extends Fragment implements PermissionAwareActivity {

    private BaseReactFragmentDelegate mDelegate;
    private Activity mActivity;

    public BaseReactFragment() {
    }

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     * e.g. "MoviesApp"
     */
    protected @Nullable
    String getMainComponentName() {
        return null;
    }

    /**
     * Called at construction time, override if you have a custom delegate implementation.
     */
    protected BaseReactFragmentDelegate createReactFragmentDelegate() {
        return new BaseReactFragmentDelegate(this, getMainComponentName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate = createReactFragmentDelegate();
        mActivity = getActivity();
        mDelegate.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mDelegate.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view,
                              @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDelegate.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mDelegate.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDelegate.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDelegate.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mDelegate.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void requestPermissions(
            String[] permissions,
            int requestCode,
            PermissionListener listener) {
        mDelegate.requestPermissions(permissions, requestCode, listener);
    }

    protected final ReactNativeHost getReactNativeHost() {
        return mDelegate.getReactNativeHost();
    }

    protected final ReactInstanceManager getReactInstanceManager() {
        return mDelegate.getReactInstanceManager();
    }

    protected final void loadApp(String appKey) {
        mDelegate.loadApp(appKey);
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return mActivity.checkPermission(permission, pid, uid);
    }

    @RequiresApi(api = VERSION_CODES.M)
    @Override
    public int checkSelfPermission(String permission) {
        return mActivity.checkSelfPermission(permission);
    }
}
