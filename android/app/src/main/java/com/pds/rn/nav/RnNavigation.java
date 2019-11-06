package com.pds.rn.nav;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.pds.rn.route.BaseRouter;
import com.pds.rn.route.ExceptionRouter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: pengdaosong
 * CreateTime:  2019-11-05 17:38
 * Email：pengdaosong@medlinker.com
 * Description:
 */
public class RnNavigation {

    private static final String MED = "rn_schema:";

    public static MedRouter withUrl(String moduleName, String routeName) {
        return withUrl(moduleName, routeName, null);
    }

    public static MedRouter withUrl(String moduleName, String routeName, @Nullable String extra) {
        return withUrl(RnRouter.createJumpUrl(moduleName, routeName, extra));
    }

    public static MedRouter withUrl(String targetUrl) {
        return new MedRouter(targetUrl);
    }

    public static class MedRouter {

        private Uri uri = Uri.EMPTY;
        private String schema = "";
        private String host = "";
        private String path = "";
        private Map<String, String> params = new HashMap<>();

        private MedRouter(String targetUrl) {
            if (TextUtils.isEmpty(targetUrl)) {
                return;
            }

            if (targetUrl.startsWith("/")) {
                targetUrl = MED + targetUrl;
            }
            Uri uri = Uri.parse(targetUrl);
            if (uri != null) {
                this.uri = uri;
                this.schema = uri.getScheme();
                this.host = uri.getHost();
                this.path = uri.getPath();
                try {
                    for (String key : uri.getQueryParameterNames()) {
                        this.params.put(key, uri.getQueryParameter(key));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public Uri getUri() {
            return uri;
        }

        public String getSchema() {
            return schema;
        }

        public String getHost() {
            return host;
        }

        public String getPath() {
            return path;
        }

        public Map<String, String> getParams() {
            return params;
        }

        @Override
        public String toString() {
            return "MedRouter{" +
                    "uri=" + uri +
                    ", schema='" + schema + '\'' +
                    ", host='" + host + '\'' +
                    ", path='" + path + '\'' +
                    ", params=" + params +
                    '}';
        }

        public BaseRouter queryTarget() {
            return queryTarget(false);
        }

        public BaseRouter queryTarget(boolean isNeedLogin) {
            try {
                Class<? extends BaseRouter> aClass = RoutePathMap.getRouteClass(path);
                if (aClass != null) {
                    return aClass.getDeclaredConstructor(MedRouter.class).newInstance(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ExceptionRouter(this);
        }

    }
}
