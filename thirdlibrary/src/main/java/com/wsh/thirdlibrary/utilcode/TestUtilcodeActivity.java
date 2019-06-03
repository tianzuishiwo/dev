package com.wsh.thirdlibrary.utilcode;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.wsh.thirdlibrary.R;

/**
 * 使用说明地址：
 *
 * https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/README-CN.md
 * https://github.com/Blankj/AndroidUtilCode/blob/master/subutil/README-CN.md
 */
//public class TestUtilcodeActivity extends ActivityCompat {
public class TestUtilcodeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        TextView tvTime = findViewById(R.id.tv_utilcode_time);
        tvTime.setText(TimeUtils.getChineseWeek(System.currentTimeMillis()));
    }
}
