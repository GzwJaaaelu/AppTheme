package com.google.jaaaelu.gzw.apptheme.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.jaaaelu.gzw.apptheme.R;
import com.google.jaaaelu.gzw.apptheme.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.tv_content)
    TextView mContent;
    @BindView(R.id.activity_root)
    NestedScrollView mActivityRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scrolling;
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar);
        getWindow().setStatusBarColor(mThemeColor);
        //  夜间模式修改字体(这里及懒得写了)
        if (mThemeColor == getResources().getColor(R.color.colorNight)) {
            mContent.setTextColor(getResources().getColor(R.color.colorWhite));
        }
        mFab.setOnClickListener(v ->
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );
    }

    @Override
    protected void getData() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return super.isSupportSwipeBack();
    }
}
