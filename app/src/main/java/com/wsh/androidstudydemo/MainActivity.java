package com.wsh.androidstudydemo;

import android.widget.TextView;

import com.wsh.androidstudydemo.base.BaseActivity;
import com.wsh.androidstudydemo.ui.views.MyViewActivity;

import butterknife.Bind;


public class MainActivity extends BaseActivity {

    @Bind(R.id.tv_main)
    TextView tvMain;

    @Override
    protected void initView() {
        super.initView();
        tvMain.setText("butterkite222");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        autoOpenActivity();
    }

    private void autoOpenActivity() {
        if (autoOpenActivityEnable()) {
            openActivity(getTargetActivity());
        }
    }

    protected boolean autoOpenActivityEnable() {
        return true;
    }

    protected Class<?> getTargetActivity() {
        return MyViewActivity.class;
    }

}
