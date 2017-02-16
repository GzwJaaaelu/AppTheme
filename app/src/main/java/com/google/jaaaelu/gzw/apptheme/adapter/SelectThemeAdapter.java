package com.google.jaaaelu.gzw.apptheme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.jaaaelu.gzw.apptheme.R;
import com.google.jaaaelu.gzw.apptheme.config.ThemeColorConstant;
import com.google.jaaaelu.gzw.apptheme.util.SharePreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.jaaaelu.gzw.apptheme.util.SharePreferencesHelper.LASE_THEME_COLOR;
import static com.google.jaaaelu.gzw.apptheme.util.SharePreferencesHelper.THEME_COLOR;

/**
 * Created by admin on 2017/2/12.
 */

public class SelectThemeAdapter extends RecyclerView.Adapter<SelectThemeAdapter.SelectThemeHolder> {
    private SparseArray<Integer> mThemeColorArray;
    private Context mContext;
    private int mLastSelectThemeColor = 0;
    private int mCurrSelectThemeColor = 0;
    private ThemeColorChangeListener mListener;

    public SelectThemeAdapter(Context context, ThemeColorChangeListener listener) {
        mContext = context;
        mListener = listener;
        initData();
        setCurrSelectThemeColor();
    }

    /**
     * 准好好要用的数据
     */
    private void initData() {
        mThemeColorArray = new SparseArray<>();
        mThemeColorArray.put(ThemeColorConstant.RED_THEME, mContext.getResources().getColor(R.color.colorPrimary));
        mThemeColorArray.put(ThemeColorConstant.PINK_THEME, mContext.getResources().getColor(R.color.colorPink));
        mThemeColorArray.put(ThemeColorConstant.PURPLE_THEME, mContext.getResources().getColor(R.color.colorPurple));
        mThemeColorArray.put(ThemeColorConstant.INDIGO_THEME, mContext.getResources().getColor(R.color.colorIndigo));
        mThemeColorArray.put(ThemeColorConstant.TEAL_THEME, mContext.getResources().getColor(R.color.colorTeal));
        mThemeColorArray.put(ThemeColorConstant.BROWN_THEME, mContext.getResources().getColor(R.color.colorBrown));
        mThemeColorArray.put(ThemeColorConstant.BLUE_GREY_THEME, mContext.getResources().getColor(R.color.colorBlueGrey));
        mThemeColorArray.put(ThemeColorConstant.NIGHT_THEME, mContext.getResources().getColor(R.color.colorNight));
    }


    @Override
    public SelectThemeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_theme_color, parent, false);
        return new SelectThemeHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectThemeHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return mThemeColorArray.size();
    }

    public void setCurrSelectThemeColor() {
        switch (SharePreferencesHelper.getInt(LASE_THEME_COLOR, THEME_COLOR)) {
            case ThemeColorConstant.RED_THEME:
                mCurrSelectThemeColor = ThemeColorConstant.RED_THEME;
                break;
            case ThemeColorConstant.PINK_THEME:
                mCurrSelectThemeColor = ThemeColorConstant.PINK_THEME;
                break;
            case ThemeColorConstant.PURPLE_THEME:
                mCurrSelectThemeColor = ThemeColorConstant.PURPLE_THEME;
                break;
            case ThemeColorConstant.INDIGO_THEME:
                mCurrSelectThemeColor = ThemeColorConstant.INDIGO_THEME;
                break;
            case ThemeColorConstant.TEAL_THEME:
                mCurrSelectThemeColor = ThemeColorConstant.TEAL_THEME;
                break;
            case ThemeColorConstant.BROWN_THEME:
                mCurrSelectThemeColor = ThemeColorConstant.BROWN_THEME;
                break;
            case ThemeColorConstant.BLUE_GREY_THEME:
                mCurrSelectThemeColor = ThemeColorConstant.BLUE_GREY_THEME;
                break;
            case ThemeColorConstant.NIGHT_THEME:
                mCurrSelectThemeColor = ThemeColorConstant.NIGHT_THEME;
                break;
        }
    }

    class SelectThemeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_theme_color)
        ImageView mThemeColor;
        @BindView(R.id.iv_select_this_color)
        ImageView mSelectThisColor;

        public SelectThemeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mThemeColor.setOnClickListener(v -> {
                mLastSelectThemeColor = mCurrSelectThemeColor;
                mCurrSelectThemeColor = getAdapterPosition();
                mListener.themeColorChange(mThemeColorArray.keyAt(getAdapterPosition()), mThemeColorArray.valueAt(getAdapterPosition()));
                notifyItemChanged(mLastSelectThemeColor);
                notifyItemChanged(mCurrSelectThemeColor);
            });
        }

        public void setData() {
            if (getAdapterPosition() == mCurrSelectThemeColor) {
                mSelectThisColor.setVisibility(View.VISIBLE);
            } else {
                mSelectThisColor.setVisibility(View.GONE);
            }
            mThemeColor.setBackgroundColor(mThemeColorArray.valueAt(getAdapterPosition()));
        }
    }

    public interface ThemeColorChangeListener {

        void themeColorChange(int newThemeColor, int color);
    }
}
