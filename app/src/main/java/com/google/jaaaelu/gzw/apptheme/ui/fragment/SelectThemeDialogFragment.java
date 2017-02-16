package com.google.jaaaelu.gzw.apptheme.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.jaaaelu.gzw.apptheme.R;
import com.google.jaaaelu.gzw.apptheme.adapter.SelectThemeAdapter;
import com.google.jaaaelu.gzw.apptheme.base.BaseActivity;
import com.google.jaaaelu.gzw.apptheme.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/2/12.
 */

public class SelectThemeDialogFragment extends DialogFragment {
    @BindView(R.id.rv_select_theme_color)
    RecyclerView mSelectThemeColor;
    @BindView(R.id.tv_dialog_title)
    TextView mDialogTitle;

    public static SelectThemeDialogFragment newInstance() {

        Bundle args = new Bundle();

        SelectThemeDialogFragment fragment = new SelectThemeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        View view = inflater.inflate(R.layout.fragment_select_theme_dialog, container, false);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mDialogTitle.setText("请选择任意的主题");
        mSelectThemeColor.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mSelectThemeColor.setAdapter(new SelectThemeAdapter(getContext(), (MainActivity)getActivity()));
    }
}
