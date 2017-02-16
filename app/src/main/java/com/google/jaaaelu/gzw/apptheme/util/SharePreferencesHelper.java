package com.google.jaaaelu.gzw.apptheme.util;

import android.content.SharedPreferences;
import android.content.Context;

import com.google.jaaaelu.gzw.apptheme.AppThemeApplication;

/**
 * Created by zw on 16/7/8.
 */
public class SharePreferencesHelper {
    private static final String TAG = "SharePreferencesHelper";
    public static final String THEME_COLOR = "theme_color";
    public static final String LASE_THEME_COLOR = "lase_theme_color";
    private static final Context CONTEXT = AppThemeApplication.getInstance().getApplicationContext();

    /**
     * 用Sp存int类型数据
     *
     * @param key     键 如:USER_ACCOUNT
     * @param value   值 如:获取到的用户账号
     * @param tagName 存到那个Sp中 如:用户账号密码存到用户信息中
     */
    public static void putInt(String key, int value, String tagName) {
        SharedPreferences sp = getSharePreferences(tagName);
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    private static SharedPreferences getSharePreferences(String tagName) {
        return CONTEXT.getSharedPreferences(tagName, Context.MODE_PRIVATE);
    }

    /**
     * 获取Sting
     *
     * @param key     存的时候的键
     * @param tagName 是哪个部分
     * @return 返回获取的到值, 默认值为0
     */
    public static int getInt(String key, String tagName) {
        SharedPreferences sp = getSharePreferences(tagName);
        return sp.getInt(key, 0);
    }

    /**
     * 清除对应部分的存过的数据
     *
     * @param tagName 对应部分 如:USER_INFO
     */
    public static void cleanSomeSp(String tagName) {
        SharedPreferences sp = getSharePreferences(tagName);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

}
