
package com.google.jaaaelu.gzw.apptheme.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.jaaaelu.gzw.apptheme.R;
import com.google.jaaaelu.gzw.apptheme.adapter.SelectThemeAdapter;
import com.google.jaaaelu.gzw.apptheme.config.ThemeColorConstant;
import com.google.jaaaelu.gzw.apptheme.config.ThemeConfig;
import com.google.jaaaelu.gzw.apptheme.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;


/**
 * Created by admin on 2016/12/13.
 */

public abstract class BaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate {
    private long mExitTime;
    protected BGASwipeBackHelper mSwipeBackHelper;
    public int mThemeColor;
    @BindView(R.id.activity_root)
    View mActivityRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        setTheme(ThemeConfig.getInstance().getTheme(ThemeConfig.getInstance().getLastThemeColor()));
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            if (isImmersion()) {
                fullScreen();
            }
            setContentView(getLayoutId());
            ButterKnife.bind(this);
            getData();
            getThemeColor();
            setActivityBgColor(mActivityRoot);
            initView();
        } else {
            throw new IllegalArgumentException("No found the specified layout");
        }
    }

    /**
     * 沉浸式显示效果
     */
    private void fullScreen() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //  透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    /**
     * 用来获取布局Id
     *
     * @return 布局的Id值
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件和侦听
     */
    protected abstract void initView();

    /**
     * 初始化当前界面所需的数据
     */
    protected abstract void getData();

    /**
     * 是否显示透明标题栏(沉浸式)
     *
     * @return 是 or 否
     */
    protected boolean isImmersion() {
        return false;
    }

    @Override
    public void onBackPressed() {
        mSwipeBackHelper.backward();
    }

    /**
     * 退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (doubleClickExit()) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if ((SystemClock.uptimeMillis() - mExitTime) > 2000) {
                    ToastUtil.showShortToast(getString(R.string.exit_app));
                    mExitTime = SystemClock.uptimeMillis();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 是否可以双击返回键退出应用
     *
     * @return
     */
    protected boolean doubleClickExit() {
        return false;
    }

    /**
     * 获取点击事件 通过点击屏幕隐藏软键盘
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 判定是否需要隐藏
     * @param v
     * @param ev
     * @return
     */
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //  滑动返回相关重写方法

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    protected void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        //  设置了左上角的返回按钮
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    public void getThemeColor() {
        switch (ThemeConfig.getInstance().getLastThemeColor()) {
            case ThemeColorConstant.RED_THEME:
                mThemeColor = getResources().getColor(R.color.colorPrimary);
                break;
            case ThemeColorConstant.PINK_THEME:
                mThemeColor = getResources().getColor(R.color.colorPink);
                break;
            case ThemeColorConstant.PURPLE_THEME:
                mThemeColor = getResources().getColor(R.color.colorPurple);
                break;
            case ThemeColorConstant.INDIGO_THEME:
                mThemeColor = getResources().getColor(R.color.colorIndigo);
                break;
            case ThemeColorConstant.TEAL_THEME:
                mThemeColor = getResources().getColor(R.color.colorTeal);
                break;
            case ThemeColorConstant.BROWN_THEME:
                mThemeColor = getResources().getColor(R.color.colorBrown);
                break;
            case ThemeColorConstant.BLUE_GREY_THEME:
                mThemeColor = getResources().getColor(R.color.colorBlueGrey);
                break;
            case ThemeColorConstant.NIGHT_THEME:
                mThemeColor = getResources().getColor(R.color.colorNight);
                break;
        }
    }


    private int getBgColor() {
        TypedArray typedArray = getTheme().obtainStyledAttributes(new int[]{R.attr.gbg});
        int color = typedArray.getColor(0, 0);
        typedArray.recycle();
        return color;
    }

    /**
     * 设置Activity的背景色 可应用于夜间模式
     */
    public void setActivityBgColor(View root) {
        int bgColor = getBgColor();
        if (bgColor != 0) {
            root.setBackgroundColor(bgColor);
        }
    }
}
