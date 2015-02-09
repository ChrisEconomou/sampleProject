package com.chriseconomou.sampleproject.database;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesStorage {

    private static final String PREFS_NAME = "pref_storage";


    private Context mContext;

    public PreferencesStorage(Context context) {
        mContext = context;
    }


    public String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }


    public void putString(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private boolean getBoolean(String key) {
        return getSharedPreferences().getBoolean(key, true);
    }

    private void putBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }


    public void clear() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
