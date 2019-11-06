package com.pds.rn.utils;

import java.util.HashMap;

/**
 * @author pengdaosong
 */
public class FragmentRNCollector {

    private static HashMap<String, Boolean> sFragmentStateMap = new HashMap<>();

    public static void onVisible(String moduleRouterName, boolean isVisible) {
        sFragmentStateMap.put(moduleRouterName, isVisible);
    }

    public static void remove(String moduleRouterName) {
        if (sFragmentStateMap.containsKey(moduleRouterName)) {
            sFragmentStateMap.remove(moduleRouterName);
        }
    }

    public static boolean isVisible(String moduleName, String routerName) {
        String name = String.format("%s-%s", moduleName, routerName);
        if (sFragmentStateMap.containsKey(name)) {
            return sFragmentStateMap.get(name);
        }
        return false;
    }
}
