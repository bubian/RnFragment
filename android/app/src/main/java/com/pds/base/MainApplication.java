package com.pds.base;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.pds.BuildConfig;
import com.pds.rn.pa.CommonReactPackage;
import java.util.Arrays;
import java.util.List;

/**
 * @author pengdaosong
 */
public class MainApplication extends Application implements ReactApplication {

  private static MainApplication mMainApplication;

  public static MainApplication getApplication() {
    return mMainApplication;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, false);
    mMainApplication = this;
  }

  /******************************Rn配置***************************************/

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new CommonReactPackage()
      );
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }
}
