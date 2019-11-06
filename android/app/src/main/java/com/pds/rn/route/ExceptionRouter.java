package com.pds.rn.route;

import android.content.Context;
import com.pds.rn.nav.RnNavigation;

public class LostPageRouter extends BaseRouter {

    public LostPageRouter(RnNavigation.MedRouter medRouter) {
        super(medRouter);
    }

    @Override
    public String getRouterKey() {
        return null;
    }

    @Override
    public Class getTargetClass() {
        return null;
    }

    @Override
    public void navigation(Context context) {
        
    }
}
