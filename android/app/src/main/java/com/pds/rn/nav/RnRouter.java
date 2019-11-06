package com.pds.rn.nav;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.pds.rn.rectactivity.RnActivity;
import com.pds.rn.rectactivity.RnKey;
import com.pds.rn.route.BaseRouter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RnRouter extends BaseRouter {

    public static final String NEW_REACT_NATIVE = "/rn";

    public RnRouter(RnNavigation.MedRouter medRouter) {
        super(medRouter);
    }

    @Override
    protected boolean needInterrupt(Context context, int requestCode) {
        String moduleName = mMedRouter.getParams().get(RnKey.MODULE_NAME);
        String routeName = mMedRouter.getParams().get(RnKey.ROUTE_NAME);
        String extraStr = mMedRouter.getParams().get(RnKey.DATA_EXTRA);
        String path = mMedRouter.getPath();
        RnActivity.launchReactActivity(context, moduleName, routeName, extraStr, requestCode);
        return true;
    }

    @Override
    public String getRouterKey() {
        return NEW_REACT_NATIVE;
    }

    @Override
    public Class getTargetClass() {
        return null;
    }

    public static String createJumpUrl(String moduleName, String routeName, @Nullable String extra) {
        if (!TextUtils.isEmpty(extra)) {
            try {
                extra = URLEncoder.encode(extra, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                extra = null;
            }
        }
        return buildRoute(NEW_REACT_NATIVE,moduleName,routeName,extra);
    }

    public static String buildRoute(String baseUrl, String moduleName, String routeName,
            @Nullable String extra){
        StringBuilder builder = new StringBuilder();
        builder.append(baseUrl)
                .append("?").append(RnKey.MODULE_NAME).append("=").append(moduleName)
                .append("&").append(RnKey.ROUTE_NAME).append("=").append(routeName);
        if (!TextUtils.isEmpty(extra)){
            builder.append("&").append(RnKey.DATA_EXTRA).append("=").append(extra);
        }
        return builder.toString();
    }
}
