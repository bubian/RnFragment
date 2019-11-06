package com.pds.rn.rectactivity;

import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_PAGE_ANGEL_QA;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_PAGE_CS_HOME;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_PAGE_TS_HOME;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.facebook.react.ReactActivityDelegate;
import com.medlinker.base.utils.StringUtil;
import javax.annotation.Nullable;
import net.medlinker.medlinker.common.track.TrackManager;

/**
 * 便于传值到react
 *
 * @author jiantao
 * @date 2017/12/7
 */

public class MedReactActivityDelegate extends ReactActivityDelegate {

    private Bundle bundle;

    public MedReactActivityDelegate(Activity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
    }

    public MedReactActivityDelegate(FragmentActivity fragmentActivity, @Nullable String mainComponentName) {
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
        TrackManager.getInstance().onJumpPage(getPageName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPageName() != null) {
            TrackManager.getInstance().onResumePage(getPageName());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getPageName() != null) {
            TrackManager.getInstance().onPausePage(getPageName());
        }
    }

    public String getPageName() {
        String routeName = "react-native-page";
        if (bundle != null) {
            routeName = bundle.getString("routeName");
        }
        switch (routeName) {
            case RN_PAGE_CS_HOME:
                routeName = StringUtil.buildPage("myCustomerServices");
                break;
            case RN_PAGE_TS_HOME:
                routeName = StringUtil.buildPage("spaceTimeList");
                break;
            case RN_PAGE_ANGEL_QA:
                routeName = StringUtil.buildPage("answerindex");
                break;
            default:
                routeName = StringUtil.buildPage(routeName);
                break;
        }
        return routeName;
    }
}
