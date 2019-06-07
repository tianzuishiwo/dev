package com.wsh.thirdlibrary.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.wsh.thirdlibrary.R;
import com.wsh.thirdlibrary.dialog.model.DialogInfo;

//import mobileclient.gowild.com.platformlibrary.R;
//import mobileclient.gowild.com.platformlibrary.dialog.model.DialogInfo;
//import mobileclient.gowild.com.platformlibrary.util.AvoidFastClickUtil;
//import mobileclient.gowild.com.platformlibrary.util.BeanUtils;
//import mobileclient.gowild.com.platformlibrary.util.StringUtils;

/**
 * Created by zy on 2017/11/6.
 */

public class GowildConfirmDialog extends GowildBaseDialog {

    private TextView mGowildBtnCancel;
    private TextView mGowildBtnConfirm;
    private TextView mGowildTvContent;
    private TextView mGowilTvTitle;

    private DialogInfo info;
    private int        type;
    private boolean    titleVisible;

    private ConfirmCallback confirmCallback;
    private View mGowildSplit;

    public static GowildConfirmDialog getInstance(Bundle bundle) {
        GowildConfirmDialog dialog = new GowildConfirmDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    public GowildConfirmDialog() {
    }

    public void setConfirmCallback(ConfirmCallback confirmCallback) {
        this.confirmCallback = confirmCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogInfo.DialogBuildInfo build = (DialogInfo.DialogBuildInfo) bundle.getSerializable(TAG);
            if (null != build) {
                info = build.create();
            }
//            if (BeanUtils.isNotEmpty(info)) {
                type = info.getType();
                mDialogTag = info.getTag();
                titleVisible = info.isTitleVisible();
//            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog_confirm, container, false);

        mGowildBtnCancel = view.findViewById(R.id.btn_cancel);
        mGowildBtnConfirm = view.findViewById(R.id.btn_confirm);
        mGowildTvContent = view.findViewById(R.id.tv_dialog_centent);
        mGowilTvTitle = view.findViewById(R.id.tv_dialog_title);
        mGowilTvTitle.setVisibility(titleVisible ? View.VISIBLE : View.GONE);
        mGowildSplit = view.findViewById(R.id.line_split);

        mGowildBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (AvoidFastClickUtil.isFastDoubleClick())
//                    return;
                if (null != confirmCallback) {
//                    Object data = BeanUtils.isNotEmpty(info.getCallbackData()) ? info.getCallbackData() : null;
                    confirmCallback.onConfirm(type, info.getCallbackData());
                }
                dismissSelf();
            }
        });

        mGowildBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (AvoidFastClickUtil.isFastDoubleClick())
//                    return;
                if (null != confirmCallback) {
                    confirmCallback.onCancel(type);
                }
                dismissSelf();
            }
        });

//        if (StringUtils.isNotEmpty(info.getTitle())) {
            mGowilTvTitle.setText(info.getContent());
//        }

        if (!info.isMoveMent()) {
//            if (StringUtils.isNotEmpty(info.getContent())) {
                mGowildTvContent.setText(info.getContent());
//            }
        } else {
//            if (StringUtils.isNotEmpty(info.getSpannableContent().toString())) {
                mGowildTvContent.setText(info.getSpannableContent());
//            }
        }

        if (!StringUtils.isEmpty(info.getPositiveText())) {
            mGowildBtnConfirm.setText(info.getPositiveText());
        } else {
//            mGowildBtnConfirm.setText(getString(R.string.ok));
            mGowildBtnConfirm.setText("确定");
        }


        if (!StringUtils.isEmpty(info.getNegativeText())) {
            mGowildBtnCancel.setText(info.getNegativeText());
        } else {
//            mGowildBtnCancel.setText(getString(R.string.cancel));
            mGowildBtnCancel.setText("取消");
        }

        if(!info.isCancelButtonVisible()) {
            mGowildBtnCancel.setVisibility(View.GONE);
            mGowildSplit.setVisibility(View.GONE);
            mGowildBtnConfirm.setBackgroundResource(R.drawable.bottom_round_shape_selector);
        }

        if (info.isMoveMent()) {
            mGowildTvContent.setMovementMethod(LinkMovementMethod.getInstance());
        }

        if(info.getTextGravity() != 0) {
            mGowildTvContent.setGravity(info.getTextGravity());
        }

        return view;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if(confirmCallback != null) {
            confirmCallback.onCancel(-1);
        }
    }

    public interface ConfirmCallback {
        void onConfirm(int type, Object object);

        void onCancel(int type);
    }
}
