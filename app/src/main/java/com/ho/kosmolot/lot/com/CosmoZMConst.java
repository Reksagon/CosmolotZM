package com.ho.kosmolot.lot.com;

import android.net.Uri;
import android.webkit.ValueCallback;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class CosmoZMConst {
    static int profileCode = 666;
    static int Code = 33333;
    static ValueCallback<Uri[]> CallBack;
    static Uri URL;
    static FirebaseRemoteConfig Firebase;

    public static void SetBase()
    {
        Firebase = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().build();
        Firebase.setDefaultsAsync(R.xml.cosmozm_url);
        Firebase.setConfigSettingsAsync(configSettings);
    }

    public static int getCode() {
        return Code;
    }

    public static void setCode(int code) {
        Code = code;
    }

    public static ValueCallback<Uri[]> getCallBack() {
        return CallBack;
    }

    public static void setCallBack(ValueCallback<Uri[]> callBack) {
        CallBack = callBack;
    }

    public static Uri getURL() {
        return URL;
    }

    public static void setURL(Uri URL) {
        CosmoZMConst.URL = URL;
    }

    public static FirebaseRemoteConfig getFirebase() {
        return Firebase;
    }

    public static void setFirebase(FirebaseRemoteConfig firebase) {
        Firebase = firebase;
    }

    public static int getProfileCode() {
        return profileCode;
    }

    public static void setProfileCode(int profileCode) {
        CosmoZMConst.profileCode = profileCode;
    }
}
