package com.pds.base.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import java.io.Serializable;
import java.util.List;

/**
 * intent跳转传递数据
 *
 * @author hmy
 */
public class IntentHelper {

    public static final int REQUEST_INVALID = -1;
    public static final String DATA_KEY = "DATA_KEY";

    /***/
    public static void startActivity(Activity context, Class<?> activity) {
        context.startActivity(new Intent(context, activity));
    }

    /***/
    public static void startActivity(Activity context, Class<?> activity, int requestCode) {
        context.startActivityForResult(new Intent(context, activity), requestCode);
    }

    /***/
    public static void startActivity(Fragment fragment, Class<?> activity, int requstCode) {
        getRootFragment(fragment).startActivityForResult(new Intent(fragment.getActivity(), activity), requstCode);
    }

    /***/
    public static void startActivity(Activity context, Class<?> activity, int requestCode, boolean data) {
        Intent intent = new Intent(context, activity);
        if (isNotNull(data)) {
            intent.putExtra(DATA_KEY, data);
        }

        if (requestCode > REQUEST_INVALID) {
            context.startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    /***/
    public static void startActivity(Fragment fragment, Class<?> activity, int requestCode, boolean data) {
        Intent intent = new Intent(fragment.getActivity(), activity);
        if (isNotNull(data)) {
            intent.putExtra(DATA_KEY, data);
        }

        if (requestCode > REQUEST_INVALID) {
            getRootFragment(fragment).startActivityForResult(intent, requestCode);
        } else {
            getRootFragment(fragment).startActivity(intent);
        }
    }

    /***/
    public static void startActivity(Activity context, Class<?> activity, int requestCode, String data) {
        Intent intent = new Intent(context, activity);
        if (isNotNull(data)) {
            intent.putExtra(DATA_KEY, data);
        }

        if (requestCode > REQUEST_INVALID) {
            context.startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    /***/
    public static void startActivity(Fragment fragment, Class<?> activity, int requestCode, String data) {
        Intent intent = new Intent(fragment.getActivity(), activity);
        if (isNotNull(data)) {
            intent.putExtra(DATA_KEY, data);
        }

        if (requestCode > REQUEST_INVALID) {
            getRootFragment(fragment).startActivityForResult(intent, requestCode);
        } else {
            getRootFragment(fragment).startActivity(intent);
        }
    }

    /***/
    public static void startActivity(Activity context, Class<?> activity, int requestCode, Serializable data) {
        Intent intent = new Intent(context, activity);
        if (isNotNull(data)) {
            intent.putExtra(DATA_KEY, data);
        }

        if (requestCode > REQUEST_INVALID) {
            context.startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    /***/
    public static void startActivity(Fragment fragment, Class<?> activity, int requestCode, Serializable data) {
        Intent intent = new Intent(fragment.getActivity(), activity);
        if (isNotNull(data)) {
            intent.putExtra(DATA_KEY, data);
        }

        if (requestCode > REQUEST_INVALID) {
            getRootFragment(fragment).startActivityForResult(intent, requestCode);
        } else {
            getRootFragment(fragment).startActivity(intent);
        }
    }

    /***/
    public static void startActivity(Activity context, Class<?> activity, int requestCode, Parcelable data) {
        Intent intent = new Intent(context, activity);
        if (isNotNull(data)) {
            intent.putExtra(DATA_KEY, data);
        }

        if (requestCode > REQUEST_INVALID) {
            context.startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    /***/
    public static void startActivity(Fragment fragment, Class<?> activity, int requestCode, Parcelable data) {
        Intent intent = new Intent(fragment.getActivity(), activity);
        if (isNotNull(data)) {
            intent.putExtra(DATA_KEY, data);
        }

        if (requestCode > REQUEST_INVALID) {
            getRootFragment(fragment).startActivityForResult(intent, requestCode);
        } else {
            getRootFragment(fragment).startActivity(intent);
        }
    }

    /***/
    public static <T extends Serializable> T getExtraObj(Intent intent, String key) {
        return (T) intent.getSerializableExtra(key);
    }

    /***/
    public static <T extends Serializable> T getExtraObj(Bundle bundle, String key) {
        return (T) bundle.getSerializable(key);
    }

    /***/
    public static <T extends Serializable> T getExtraObj(Intent intent) {
        return getExtraObj(intent, DATA_KEY);
    }

    /***/
    public static <T extends Serializable> T getExtraObj(Bundle bundle) {
        return getExtraObj(bundle, DATA_KEY);
    }

    /***/
    public static <T extends Parcelable> T getExtraParcelable(Intent intent, String key) {
        return (T) intent.getParcelableExtra(key);
    }

    /***/
    public static <T extends Parcelable> T getExtraParcelable(Bundle bundle) {
        return (T) bundle.getParcelable(DATA_KEY);
    }

    /***/
    public static <T extends Parcelable> T getExtraParcelable(Intent intent) {
        return getExtraParcelable(intent, DATA_KEY);
    }

    /***/
    public static String getExtraStr(Intent intent, String key) {
        return intent.getStringExtra(key);
    }

    /***/
    public static String getExtraStr(Intent intent) {
        return intent.getStringExtra(DATA_KEY);
    }

    /***/
    public static String getExtraStr(Bundle bundle) {
        return bundle.getString(DATA_KEY);
    }

    /***/
    public static boolean getExtraBool(Intent intent, String key) {
        return intent.getBooleanExtra(key, false);
    }

    /***/
    public static boolean getExtraBool(Intent intent) {
        return intent.getBooleanExtra(DATA_KEY, false);
    }

    /***/
    public static int getExtraInt(Intent intent, String key) {
        return intent.getIntExtra(key, 0);
    }

    /***/
    public static int getExtraInt(Intent intent) {
        return intent.getIntExtra(DATA_KEY, 0);
    }

    /***/
    public static Long getExtraLong(Intent intent, String key) {
        return intent.getLongExtra(key, 0);
    }

    /***/
    public static Long getExtraLong(Intent intent) {
        return intent.getLongExtra(DATA_KEY, 0);
    }

    /***/
    public static List getExtraArrayList(Intent intent) {
        return (List) intent.getSerializableExtra(DATA_KEY);
    }

    /***/
    public static void setExtraObj(Intent intent, String key, Serializable value) {
        intent.putExtra(key, value);
    }

    /***/
    public static void setExtraObj(Intent intent, Serializable value) {
        intent.putExtra(DATA_KEY, value);
    }

    /***/
    public static void setExtraParcelable(Intent intent, String key, Parcelable value) {
        intent.putExtra(key, value);
    }

    /***/
    public static void setExtraParcelable(Intent intent, Parcelable value) {
        intent.putExtra(DATA_KEY, value);
    }

    /***/
    public static void setExtraStr(Intent intent, String key, String value) {
        intent.putExtra(key, value);
    }

    /***/
    public static void setExtraStr(Intent intent, String value) {
        intent.putExtra(DATA_KEY, value);
    }

    /***/
    public static void setExtraInt(Intent intent, String key, int value) {
        intent.putExtra(key, value);
    }

    /***/
    public static void setExtraInt(Intent intent, int value) {
        intent.putExtra(DATA_KEY, value);
    }

    /***/
    public static void setExtraBool(Intent intent, String key, boolean value) {
        intent.putExtra(key, value);
    }

    /***/
    public static void setExtraBool(Intent intent, boolean value) {
        intent.putExtra(DATA_KEY, value);
    }

    /***/
    public static void setExtraStr(Bundle bundle, String value) {
        bundle.putString(DATA_KEY, value);
    }

    /***/
    public static void setExtraParcelable(Bundle bundle, Parcelable value) {
        bundle.putParcelable(DATA_KEY, value);
    }

    /***/
    public static void finishOk(Activity activity, Object object) {
        finishOk(activity, DATA_KEY, object);
    }

    /**
     * @param object 非list
     */
    public static void finishOk(Activity activity, String key, Object object) {
        Intent intent = new Intent();
        if (object instanceof Serializable) {
            intent.putExtra(key, (Serializable) object);
        } else if (object instanceof Parcelable) {
            intent.putExtra(key, (Parcelable) object);
        } else if (object instanceof CharSequence) {
            intent.putExtra(key, (CharSequence) object);
        }
        if (activity != null) {
            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        }
    }

    /**
     * 得到根Fragment
     *
     * @return
     */
    private static Fragment getRootFragment(Fragment mFragment) {
        Fragment parentFragment = mFragment.getParentFragment();
        if (parentFragment == null) {
            return mFragment;
        }
        Fragment fragment = parentFragment;
        while (parentFragment != null) {
            fragment = parentFragment;
            parentFragment = parentFragment.getParentFragment();
        }
        return fragment;
    }

    private static Boolean isNotNull(Object... value) {
        for (Object data : value) {
            if (data == null) {
                return false;
            }
        }
        return true;
    }
}
