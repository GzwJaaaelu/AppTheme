package com.google.jaaaelu.gzw.apptheme.config;

import com.google.jaaaelu.gzw.apptheme.R;
import com.google.jaaaelu.gzw.apptheme.util.SharePreferencesHelper;

import static com.google.jaaaelu.gzw.apptheme.util.SharePreferencesHelper.LASE_THEME_COLOR;
import static com.google.jaaaelu.gzw.apptheme.util.SharePreferencesHelper.THEME_COLOR;

public class ThemeConfig {
    private int lastThemeColor;
    private static volatile ThemeConfig singleton;

    private ThemeConfig() {
        //  获取最后一次存起来的颜色值
        int saveColor = SharePreferencesHelper.getInt(LASE_THEME_COLOR, THEME_COLOR);
        if (0 == saveColor) {
            lastThemeColor = ThemeColorConstant.RED_THEME;
        } else {
            lastThemeColor = saveColor;
        }
    }

    public static ThemeConfig getInstance() {
        if (singleton == null) {
            synchronized (ThemeConfig.class) {
                if (singleton == null) {
                    singleton = new ThemeConfig();
                }
            }
        }
        return singleton;
    }

    /**
     * 通过目标主主题颜色返回主题颜色
     * @param targetThemeColor  选定的目标主题颜色
     * @return
     */
    public int getTheme(int targetThemeColor) {
        int theme = 0;
        switch (targetThemeColor) {
            case ThemeColorConstant.RED_THEME:
                theme = R.style.AppTheme;
                lastThemeColor = ThemeColorConstant.RED_THEME;
                break;
            case ThemeColorConstant.PINK_THEME:
                theme = R.style.PinkTheme;
                lastThemeColor = ThemeColorConstant.PINK_THEME;
                break;
            case ThemeColorConstant.PURPLE_THEME:
                theme = R.style.PurpleTheme;
                lastThemeColor = ThemeColorConstant.PURPLE_THEME;
                break;
            case ThemeColorConstant.INDIGO_THEME:
                theme = R.style.IndigoTheme;
                lastThemeColor = ThemeColorConstant.INDIGO_THEME;
                break;
            case ThemeColorConstant.TEAL_THEME:
                theme = R.style.TealTheme;
                lastThemeColor = ThemeColorConstant.TEAL_THEME;
                break;
            case ThemeColorConstant.BROWN_THEME:
                theme = R.style.BrownTheme;
                lastThemeColor = ThemeColorConstant.BROWN_THEME;
                break;
            case ThemeColorConstant.BLUE_GREY_THEME:
                theme = R.style.BlueGreyTheme;
                lastThemeColor = ThemeColorConstant.BLUE_GREY_THEME;
                break;
            case ThemeColorConstant.NIGHT_THEME:
                theme = R.style.NightTheme;
                lastThemeColor = ThemeColorConstant.NIGHT_THEME;
                break;
        }
        //  每次改变后存一下
        SharePreferencesHelper.putInt(LASE_THEME_COLOR, lastThemeColor, THEME_COLOR);
        return theme;
    }

    /**
     * 获取最后上一次的主体颜色
     * @return
     */
    public int getLastThemeColor() {
        return lastThemeColor;
    }
}