package com.wsh.androidstudydemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initView();
        initEvent();
        initData();
//        autoOpenActivity();
    }



    protected void openActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    protected void initData() {

    }

    protected void initEvent() {

    }

    protected void initView() {

    }

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
