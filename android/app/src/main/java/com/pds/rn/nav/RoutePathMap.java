package com.pds.rn.nav;

import com.pds.rn.route.BaseRouter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: pengdaosong
 * CreateTime:  2019-11-05 17:46
 * Email：pengdaosong@medlinker.com
 * Description:
 */
public class RoutePathMap {
    private static final Map<String, Class<? extends BaseRouter>> ROUTER_MAP = new HashMap<>();
    static Class<? extends BaseRouter> getRouteClass(String path) {
        return ROUTER_MAP.get(path);
    }
}
