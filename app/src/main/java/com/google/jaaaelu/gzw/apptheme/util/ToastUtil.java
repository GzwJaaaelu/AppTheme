package com.google.jaaaelu.gzw.apptheme.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.jaaaelu.gzw.apptheme.AppThemeApplication;


/**
 * Created by admin on 2016/11/8.
 */

public class ToastUtil {
    private static Context context;
    private static Toast toast;
    private static View view;

    private ToastUtil() {
    }

    @SuppressLint("ShowToast")
    private static void getToast(Context context) {
        if (toast == null) {
            toast = new Toast(context);
        }
        if (view == null) {
            view = Toast.makeText(context, "", Toast.LENGTH_SHORT).getView();
        }
        toast.setView(view);
    }

    public static void showShortToast(CharSequence msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public static void showShortToast(int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(CharSequence msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }

    public static void showLongToast(int resId) {
        showToast(resId, Toast.LENGTH_LONG);
    }

    private static void showToast(CharSequence msg,
                                  int duration) {
        context = AppThemeApplication.getInstance().getApplicationContext();
        try {
            getToast(context);
            toast.setText(msg);
            toast.setDuration(duration);
            toast.show();
        } catch (Exception e) {

        }
    }

    private static void showToast(int resId, int duration) {
        context = AppThemeApplication.getInstance().getApplicationContext();
        try {
            if (resId == 0) {
                return;
            }
            getToast(context);
            toast.setText(resId);
            toast.setDuration(duration);
            toast.show();
        } catch (Exception e) {

        }
    }

}
