package net.medlinker.medlinker.router;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.medlinker.base.utils.ToastUtil;

import net.medlinker.medlinker.businese.msg.helper.ImForwardUtil;
import net.medlinker.medlinker.common.BaseActivity;
import net.medlinker.medlinker.reactnative.MedlinkerReactActivity;
import net.medlinker.medlinker.reactnative.ModuleConfig;
import net.medlinker.medlinker.router.base.BaseMedRouterMapping;
import net.medlinker.medlinker.router.base.MedRouterHelper;
import net.medlinker.medlinker.utils.RNRouteUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author <a href="mailto:tql@medlinker.net">tqlmorepassion</a>
 * @version 6.0
 * @description //新的RN协议，时空后启用
 * @time 2018/2/6
 */
public class RnRouter extends BaseMedRouterMapping {

    /**
     * rn 界面
     */
    public static final String REACT_NATIVE = "/rn";

    public static final String REACT_NATIVE_NEW = "/mlrn";

    // 6.1 临时协议
    public static final String REACT_NATIVE_V6_1 = "/mlrn_6_1";
    /**
     * 新协议
     */
    public static final String NEW_REACT_NATIVE = "/medrn";

    public RnRouter(MedRouterHelper.MedRouter medRouter) {
        super(medRouter);
    }

    @Override
    protected boolean needInterrupt(Context context, int requestCode) {
        //不传moduleName
        String moduleName = mMedRouter.getParams().get("moduleName");
        String routeName = mMedRouter.getParams().get("routeName");
        String extraStr = mMedRouter.getParams().get("extra");
//        // debug模式，不校验
//        if (BuildConfig.DEBUG) {
//            MedlinkerReactActivity.launchReactActivity(context, moduleName, routeName, extraStr, requestCode);
//            return true;
//        }

        String path = mMedRouter.getPath();
        if (!path.equals(NEW_REACT_NATIVE)) {
            //旧协议
            moduleName = ModuleConfig.parseModule(routeName);
            if (moduleName == null) {
                try {
                    if (RNRouteUtil.getInstance().isDeleted(routeName)) {
                        ToastUtil.showMessage(context, "该页面已下架");
                    } else {
                        // 旧版协议传入的routerName没匹配到moduleName，认为需要升级
                        ImForwardUtil.showUpdateAppDialogForIm((BaseActivity) context);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (!RnRouterInterrupt.interrupt(context, moduleName, routeName)) {
                    MedlinkerReactActivity.launchReactActivity(context, moduleName, routeName, extraStr, requestCode);
                }
            }
        } else {
            //新协议
            if (RNRouteUtil.getInstance().containsRoute(moduleName, routeName)) {
                if (!RnRouterInterrupt.interrupt(context, moduleName, routeName)) {
                    MedlinkerReactActivity.launchReactActivity(context, moduleName, routeName, extraStr, requestCode);
                }
            } else {
                if (RNRouteUtil.getInstance().isDeleted(moduleName, routeName)) {
                    ToastUtil.showMessage(context, "该页面已下架");
                } else {
                    ToastUtil.showMessage(context, "抱歉，App协议版本不兼容");
                }
            }
        }
        return true;
    }

    @Override
    public String getRouterKey() {
        return REACT_NATIVE;
    }

    @Override
    public Class getTargetClass() {
        return null;
    }

    /**
     * @param moduleName
     * @param routeName
     * @param extra      调用的地方不需要encode
     * @return
     */
    public static String createJumpUrl(String moduleName, String routeName, @Nullable String extra) {
        if (!TextUtils.isEmpty(extra)) {
            try {
                extra = URLEncoder.encode(extra, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                extra = null;
            }
        }
        final String baseUrl = NEW_REACT_NATIVE.concat("?").concat("moduleName=").concat(moduleName)
                .concat("&").concat("routeName=").concat(routeName);
        return TextUtils.isEmpty(extra) ? baseUrl : baseUrl.concat("&").concat("extra=").concat(extra);
    }
}
