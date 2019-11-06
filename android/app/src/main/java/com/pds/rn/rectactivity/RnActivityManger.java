package com.pds.rn.rectactivity;

import android.app.Activity;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

public class RnActivityManger {

    private static LinkedHashMap<String, Activity> sRNActivityMap = new LinkedHashMap<>();

    public static void add(String pageId, Activity activity) {
        sRNActivityMap.put(pageId, activity);
    }

    public static void remove(String pageId) {
        if (sRNActivityMap.containsKey(pageId)) {
            sRNActivityMap.remove(pageId);
        }
    }

    public static Activity getActivityByKey(String pageId) {
        if (isActivityExist(pageId)) {
            return sRNActivityMap.get(pageId);
        }
        return null;
    }

    public static boolean isActivityExist(String pageId) {
        return sRNActivityMap.containsKey(pageId);
    }

    public static void backToTargetActivity(String pageId, OnActivityFinishCallBack callBack) {
        if (sRNActivityMap.size() == 0) {
            return;
        }
        //倒叙
        ListIterator<Map.Entry<String, Activity>> iterator =
                new ArrayList<>(sRNActivityMap.entrySet()).listIterator(sRNActivityMap.size());
        while (iterator.hasPrevious()) {
            Map.Entry<String, Activity> entry = iterator.previous();
            String itemPageId = entry.getKey();
            if (TextUtils.equals(pageId, itemPageId)) {
                break;
            } else {
                // 目标界面上面的界面关掉
                Activity itemActivity = entry.getValue();
                itemActivity.finish();
                if (callBack != null) {
                    callBack.onFinish(itemActivity);
                }
                iterator.remove();
            }
        }
    }

    public interface OnActivityFinishCallBack {
        void onFinish(Activity activity);
    }
}
