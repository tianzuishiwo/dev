package com.wsh.androidstudydemo.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wsh.androidstudydemo.R;
import com.wsh.thirdlibrary.utils.ListUtil;

import butterknife.Bind;

/**
 * Author:      wuShaoHua
 * Email:       525427151@qq.com | wushaohua0521@163.com
 * GitHub:      https://github.com/tianzuishiwo
 * Date:        2019/6/7 12:43
 * Description: dev
 */
public class BaseListActivity extends BaseActivity {

    @Bind(R.id.base_list_tv_title)
    TextView     tvTitle;
    @Bind(R.id.base_list_lv)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayout() {
        return R.layout.base_list_activity;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText(getTitleName());
        String[] itemsData = getItemsData();
        if (!ListUtil.isArrEmpty(itemsData)) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            RecycleAdapter recycleAdapter = new RecycleAdapter(itemsData, this);
            recycleAdapter.setItemListener(getItemListener());
            mRecyclerView.setAdapter(recycleAdapter);
        }
    }

    protected RecycleAdapter.ItemListener getItemListener() {
        return null;
    }


    protected String[] getItemsData() {
        return new String[]{};
    }

    protected String getTitleName() {
        return this.getClass().getSimpleName();
    }

    public static class RecycleAdapter extends RecyclerView.Adapter<viewHolder> {

        public interface ItemListener{
            void onItemClick(int position);
        }

        private String[] datas = new String[]{};
        private Context  mContext;
        private ItemListener mItemListener;

        public void setItemListener(ItemListener itemListener) {
            mItemListener = itemListener;
        }

        public RecycleAdapter(String[] datas, Context context) {
            this.datas = datas;
            mContext = context;
        }

        @NonNull

        @Override
        public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.base_list_item, viewGroup, false);
//            return new LinearViewHolder(view);
            return new viewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull viewHolder viewHolder, int position) {
            viewHolder.setData(datas[position],position,mItemListener);
        }

        @Override
        public int getItemCount() {
            return datas.length;
        }
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvName;
        private final LinearLayout mContainer;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.base_list_item_tv_name);
            mContainer = itemView.findViewById(R.id.base_list_item_container);
        }

        public void setData(String text, final int position, final RecycleAdapter.ItemListener listener) {
            mTvName.setText(text);
            mContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
