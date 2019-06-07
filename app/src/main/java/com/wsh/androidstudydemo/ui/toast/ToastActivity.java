package com.wsh.androidstudydemo.ui.toast;

import com.wsh.androidstudydemo.R;
import com.wsh.androidstudydemo.base.BaseActivity;
import com.wsh.androidstudydemo.base.BaseListActivity;
import com.wsh.thirdlibrary.utils.ToastUtils;

/**
 * Author:      wuShaoHua
 * Email:       525427151@qq.com | wushaohua0521@163.com
 * GitHub:      https://github.com/tianzuishiwo
 * Date:        2019/6/7 12:40
 * Description: dev
 */
public class ToastActivity extends BaseListActivity {
//    @Override
//    protected int getLayout() {
//        return R.layout.toast_activity;
//    }

    private String[] datas = new String[]{"数据1","数据2","数据3","数据4","数据5","数据6","数据7",};

    @Override
    protected RecycleAdapter.ItemListener getItemListener() {
        return new RecycleAdapter.ItemListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtils.showCenterToast(ToastActivity.this,datas[position]);
            }
        };
    }

    @Override
    protected String[] getItemsData() {
        return datas;
    }
}
