package com.example.appailatrieuphu;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {
    private static final String PREF_FILE = "pref_saving";
    private static SharePreference instance;

    private SharePreference() {
        //for singleton
    }

    public static SharePreference getInstance() {
        if (instance == null) {
            instance = new SharePreference();
        }
        return instance;
    }

    public void savePref(String key, String value) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        pref.edit().putString(key, value).apply();
    }

    public String getPref(String key) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return pref.getString(key, null);
    }

    public void clearPref(String key) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        pref.edit().remove(key).apply();
    }
}
