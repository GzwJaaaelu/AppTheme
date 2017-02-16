package com.google.jaaaelu.gzw.apptheme.ui.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.jaaaelu.gzw.apptheme.R;
import com.google.jaaaelu.gzw.apptheme.adapter.SelectThemeAdapter;
import com.google.jaaaelu.gzw.apptheme.base.BaseActivity;
import com.google.jaaaelu.gzw.apptheme.config.ThemeColorConstant;
import com.google.jaaaelu.gzw.apptheme.config.ThemeConfig;
import com.google.jaaaelu.gzw.apptheme.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements SelectThemeAdapter.ThemeColorChangeListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_root)
    LinearLayout mActivityRoot;
    @BindView(R.id.btn_select_theme_color)
    Button mSelectThemeColorBtn;
    @BindView(R.id.rv_select_theme_color)
    RecyclerView mSelectThemeColor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("主题切换");
        initToolbar(mToolbar);
        mSelectThemeColor.setLayoutManager(new GridLayoutManager(this, 4));
        mSelectThemeColor.setAdapter(new SelectThemeAdapter(this, this));
        getThemeColor();
        getWindow().setStatusBarColor(mThemeColor);

    }

    @Override
    protected void getData() {
        mThemeColor = ThemeConfig.getInstance().getLastThemeColor();
    }

    @OnClick(R.id.btn_select_theme_color)
    public void onClick() {
        startActivity(new Intent(this, ScrollingActivity.class));
    }

    @Override
    public void themeColorChange(int newThemeColor, int color) {
        //  保存选择的主题
        ThemeConfig.getInstance().getTheme(newThemeColor);
        mThemeColor = color;
        //  实现不重启Activity的Theme切换 本质上是需要遍历所有View
        getWindow().setStatusBarColor(mThemeColor);
        mToolbar.setBackgroundColor(mThemeColor);
        mSelectThemeColorBtn.setBackgroundColor(mThemeColor);
        getTheme().applyStyle(ThemeConfig.getInstance().getTheme(newThemeColor), true);
        setActivityBgColor(mActivityRoot);
        //  不用重启Activity了
//        recreate();
    }

    @Override
    protected boolean doubleClickExit() {
        return true;
    }
}
