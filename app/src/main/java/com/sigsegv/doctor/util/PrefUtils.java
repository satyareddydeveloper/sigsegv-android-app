package com.sigsegv.doctor.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import javax.inject.Inject;

public class PrefUtils {

    private final SharedPreferences prefs;

    @Inject
    public PrefUtils(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserToken(String token) {
        prefs.edit().putString("user-token", token).apply();
    }

    public String getUserToken() {
        return prefs.getString("user-token", "");
    }
}
