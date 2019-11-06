package com.pds.rn.rectactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.facebook.react.ReactActivityDelegate;
import javax.annotation.Nullable;

public class RnActivityDelegate extends ReactActivityDelegate {

    private Bundle bundle;

    public RnActivityDelegate(Activity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
    }

    public RnActivityDelegate(FragmentActivity fragmentActivity,
            @Nullable String mainComponentName) {
        super(fragmentActivity, mainComponentName);
    }

    @Nullable
    @Override
    protected Bundle getLaunchOptions() {
        return bundle;
    }

    public void setLaunchOptions(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
