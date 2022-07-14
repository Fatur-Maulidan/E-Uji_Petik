package com.example.e_parking;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    /** Pendeklarasian key-data berupa String, untuk sebagai wadah penyimpanan data.
     * Jadi setiap data mempunyai key yang berbeda satu sama lain */
    static final String KEY_User = "Username";
    static final String KEY_ID = "ID";
    static final String KEY_Token = "Token";

    /** Pendlakarasian Shared Preferences yang berdasarkan paramater context */
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKEY_User(Context context, String user){
        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();
        editor.putString(KEY_User, user);
        editor.apply();
    }

    public static String getKEY_User(Context context) {
        return getSharedPreference(context).getString(KEY_User,"");
    }

    public static void setKEY_Id(Context context, int id){
        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();
        editor.putInt(KEY_ID, id);
        editor.apply();
    }

    public static int getKey_Id(Context context) {
        return getSharedPreference(context).getInt(KEY_ID,0);
    }

    public static void setKEY_Token(Context context, String token){
        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();
        editor.putString(KEY_Token, token);
        editor.apply();
    }

    public static String getKEY_Token(Context context) {
        return getSharedPreference(context).getString(KEY_Token,"");
    }

    public static void clearLoggedInUser(Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_User);
        editor.remove(KEY_ID);
        editor.remove(KEY_Token);
        editor.commit();
    }
}
