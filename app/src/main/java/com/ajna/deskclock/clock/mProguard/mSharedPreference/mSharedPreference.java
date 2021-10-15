package com.ajna.deskclock.clock.mProguard.mSharedPreference;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class mSharedPreference extends Application {

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final String sharedPrefName = "DeskClock";
    private static mSharedPreference mInstance;





    public static String isFirstTimeRun = "pref_isFirstTimeRun";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public mSharedPreference(Context con) {
        mSharedPreferences = con.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
    }


    public static synchronized mSharedPreference getInstance() {
        return mInstance;
    }

    public static void clearPref() {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void clearKeyPref(String key) {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static String readStringPref(String key) {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

        return mSharedPreferences.getString(key, "");
    }

    public static void writeStringPref(String key, String data) {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

        editor = mSharedPreferences.edit();
        editor.putString(key, data);
        editor.apply();

    }

    public static Integer readIntegerPref(String key) {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

        return mSharedPreferences.getInt(key, 0);
    }

    public static void writeIntegerPref(String key, Integer data) {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

        editor = mSharedPreferences.edit();
        editor.putInt(key, data);
        editor.apply();

    }

    public static boolean readBooleanPref(String key) {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

        return mSharedPreferences.getBoolean(key, false);


    }

    public static void writeBooleanPref(String key, boolean data) {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

        editor = mSharedPreferences.edit();
        editor.putBoolean(key, data);
        editor.apply();

    }

    public static long readLongPref(String key) {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

        return mSharedPreferences.getLong(key, 0);
    }

    public static void writeLongPref(String key, long data) {
        mSharedPreferences = mInstance.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

        editor = mSharedPreferences.edit();
        editor.putLong(key, data);
        editor.apply();

    }


}
