package com.pds.rn.route;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.pds.rn.nav.RnNavigation;

public abstract class BaseRouter {

    protected final RnNavigation.MedRouter mMedRouter;
    protected final Intent mIntent = new Intent();

    public BaseRouter(RnNavigation.MedRouter medRouter) {
        this.mMedRouter = medRouter;
    }

    public abstract String getRouterKey();

    public abstract Class getTargetClass();

    protected boolean needInterrupt(Context context, int requestCode) {
        return false;
    }

    /**
     * 装数据
     *
     * @param intent
     */
    protected void packageDataInIntent(Intent intent) {
    }

    /**
     * 如果有直接使用intent自己跳转的地方，注意拦截过后的地方，一般class会返回null,如果抛出android.content.ActivityNotFoundException
     * 请在跳转的地方加一个getcompent==null判断使用navigation方法跳转。
     *
     * @param context
     * @return
     */
    public Intent getIntent(Context context) {
        if (getTargetClass() != null) {
            mIntent.setClass(context, getTargetClass());
        }
        mIntent.putExtras(new Bundle());
        packageDataInIntent(mIntent);
        if (!(context instanceof Activity)) {
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return mIntent;
    }

    public void navigation(Context context) {
        navigation(context, 0);
    }

    public void navigation(Context context, int requestCode) {
        if (needInterrupt(context, requestCode)) {
            return;
        }
        Intent intent = getIntent(context);
        if (null == intent.getComponent()) {
            return;
        }
        if (requestCode == 0) {
            context.startActivity(intent);
        } else if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivityForResult(intent, requestCode);
        }
    }
}
