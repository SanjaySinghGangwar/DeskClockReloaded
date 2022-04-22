package com.ajna.deskclock.clock.mProguard.mSharedPreference;


import android.content.Context;
import android.content.SharedPreferences;

public class AppSharePreference {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private String APP_SHARED_PREFS;

    public AppSharePreference(Context mContext) {
        this.sharedPreferences = mContext.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        this.APP_SHARED_PREFS = "DeskClock";
    }

    public void clearAllPreferences() {
        editor.clear();
        editor.commit();
    }

    public void clearPreferences(String key) {
        editor.remove(key);
        editor.apply();

    }

    public Boolean getIsTemperatureFahrenheit() {
        return sharedPreferences.getBoolean("isTemperatureFahrenheit", false);
    }

    public void setIsTemperatureFahrenheit(boolean flag) {
        editor.putBoolean("isTemperatureFahrenheit", flag);
        editor.commit();
    }

    public Boolean getIsClockSound() {
        return sharedPreferences.getBoolean("isClockSound", false);
    }

    public void setIsClockSound(boolean flag) {
        editor.putBoolean("isClockSound", flag);
        editor.commit();
    }
}
