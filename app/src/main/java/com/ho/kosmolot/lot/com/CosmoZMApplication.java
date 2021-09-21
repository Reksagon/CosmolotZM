package com.ho.kosmolot.lot.com;

import android.app.Application;
import android.util.Base64;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.onesignal.OneSignal;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CosmoZMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CosmolotZombie();
    }

    private void CosmolotZombie() {

        AppsFlyerConversionListener appsFlyerConversionListener = new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> map) {

                for(Map.Entry<String, Object> item : map.entrySet()){
                    Log.i("APS", "conversion_attribute:" + item.getKey() + " = " + item.getValue());
                }
            }

            @Override
            public void onConversionDataFail(String s) {

            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {

            }

            @Override
            public void onAttributionFailure(String s) {

            }
        };
        AppsFlyerLib.getInstance().init(
                new String(Base64.decode(getApplicationContext().getResources().getString(R.string.appflyer), Base64.DEFAULT)),
                appsFlyerConversionListener, getApplicationContext());
        AppsFlyerLib.getInstance().start(getApplicationContext());

        YandexMetricaConfig luckySevenConfig = YandexMetricaConfig.newConfigBuilder(new String
                (Base64.decode(getApplicationContext().getResources().getString(R.string.yandex), Base64.DEFAULT))).build();
        YandexMetrica.activate(getApplicationContext(), luckySevenConfig);
        YandexMetrica.enableActivityAutoTracking(this);

        OneSignal.setAppId(new String(Base64.decode(getApplicationContext().getResources().getString(R.string.onesignal), Base64.DEFAULT)));
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        OneSignal.initWithContext(getApplicationContext());
    }
}
