package com.example.rutuja.drivedemo.context_wrapper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

/**
 * Created by rupesh on 27/12/16.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private static AppController appControllerInstance;




    /**
     * Gets the instance of AppController throughout the App
     *
     * @return AppController
     */
    public static AppController getInstance() {
        return appControllerInstance;
    }

    /**
     * Get the context from AppController   throughout the App
     *
     * @return Context
     */
    public static Context getAppContext() {
        return appControllerInstance.getApplicationContext();
    }

    /**
     * Gets the instance of AppController throughout the App(Sync)
     *
     * @return Application
     */
    public static synchronized AppController getInstanceSyn() {
        return appControllerInstance;
    }

    /**
     * Save to preferences
     *
     * @param context
     * @param preferenceName
     * @param preferenceValue
     */
    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();


    }


    public static void removePerticularKeyFromPreference(Context context , String removeablekey){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(removeablekey);
        editor.commit();

    }

    public static void FCM_Token_saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences pref = context.getSharedPreferences("MyFCMPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

  /*
    * Delete From Preference
    * */

    public static void deleteAllfromPreferences(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

    }

    /**
     * Read from preferences
     *
     * @param context
     * @param preferenceName
     * @return
     */
    public static String readFromPreferences(Context context, String preferenceName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, null);
    }

    public static String FCMreadFromPreferences(Context context, String preferenceName) {
        SharedPreferences pref = context.getSharedPreferences("MyFCMPref", MODE_PRIVATE);
        return pref.getString(preferenceName, null);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        appControllerInstance = AppController.this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
