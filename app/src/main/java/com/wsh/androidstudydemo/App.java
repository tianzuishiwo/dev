package com.wsh.androidstudydemo;

import android.app.Application;

import com.wsh.thirdlibrary.utils.ToastUtils;

/**
 * Author:      wuShaoHua
 * Email:       525427151@qq.com | wushaohua0521@163.com
 * GitHub:      https://github.com/tianzuishiwo
 * Date:        2019/6/7 12:39
 * Description: dev
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(getApplicationContext());
    }
}
