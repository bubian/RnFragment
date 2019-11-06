package com.pds.rectfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import javax.annotation.Nullable;

/**
 * Author: pengdaosong.
 * <p>
 * CreateTime:  2018/11/9 4:53 PM
 * <p>
 * Email：pengdaosong@medlinker.com.
 * <p>
 * Description:
 */
public class ReactFragmentDelegate extends BaseReactFragmentDelegate {

    private Bundle mBundle;

    public ReactFragmentDelegate(Fragment fragment,
            @Nullable String mainComponentName) {
        super(fragment, mainComponentName);

    }

    public void setLaunchOptions(Bundle bundle) {
        this.mBundle = bundle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Nullable
    @Override
    protected Bundle getLaunchOptions() {
        return mBundle;
    }
}
