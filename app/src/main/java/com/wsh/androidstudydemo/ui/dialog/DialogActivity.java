package com.wsh.androidstudydemo.ui.dialog;

import com.wsh.androidstudydemo.base.BaseListActivity;

public class DialogActivity extends BaseListActivity {

    private String[] datas = new String[]{"底部弹框","顶部弹框","数据3","数据4","数据5","数据6","数据7",};

    @Override
    protected RecycleAdapter.ItemListener getItemListener() {
        return new RecycleAdapter.ItemListener() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    case 0:
                        bottomDialog();
                        break;
                    case 1:
                        topDialog();
                        break;
                    case 2:
                        break;
                }
//                ToastUtils.showCenterToast(DialogActivity.this,datas[position]);
            }
        };
    }

    private void topDialog() {
        HalfWindowDialog.newInstance()
                .setBottom(false)
                .show(getSupportFragmentManager(),"tag");
    }

    private void bottomDialog() {
        HalfWindowDialog.newInstance()
                .setBottom(true)
                .show(getSupportFragmentManager(),"tag");
    }

    @Override
    protected String[] getItemsData() {
        return datas;
    }
}
