package com.wsh.thirdlibrary.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wsh.thirdlibrary.R;
import com.wsh.thirdlibrary.dialog.model.DialogInfo;

//import mobileclient.gowild.com.platformlibrary.R;
//import mobileclient.gowild.com.platformlibrary.dialog.model.DialogInfo;

/**
 * Created by zy on 2017/11/6.
 */

public class GowildBaseDialog extends DialogFragment {
    public static final String TAG = "GowildBaseDialog";

    protected int type;

    // 是否back取消
    public boolean bIsBackAble;

    // 是否空白取消
    public boolean bIsSpaceAble;

    // 标记
    protected String mDialogTag;

    // 对话框信息
    protected DialogInfo info;

    public DialogInfo getDialogInfo() {
        return this.info;
    }

    protected View.OnClickListener mSpaceClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (bIsSpaceAble) {
                dismissSelf();
            }
        }
    };


    public GowildBaseDialog getInstanc(Bundle bundle) {
        GowildBaseDialog dialog = new GowildBaseDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    public GowildBaseDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogInfo.DialogBuildInfo build = (DialogInfo.DialogBuildInfo) bundle.getSerializable(TAG);
            info = null;
            if (null != build) {
                info = build.create();
            }
            if (info != null) {
                bIsBackAble = info.isBackAble();
                bIsSpaceAble = info.isSpaceAble();
                mDialogTag = info.getTag();
                setCancelable(bIsBackAble);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.dimAmount = 0.6f;
        window.setAttributes(layoutParams);
        getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return show(transaction, tag, true);
    }

    public int show(FragmentTransaction transaction, String tag, boolean allowStateLoss) {
        transaction.add(this, tag);
        int mBackStackId;
        if (allowStateLoss)
            mBackStackId = transaction.commitAllowingStateLoss();
        else
            mBackStackId = transaction.commit();

        return mBackStackId;
    }

    public void dismissSelf() {
        dismiss();
    }

    @SuppressWarnings("unused")
    public void setOnSpaceClickListener(View.OnClickListener onSpaceClickListener) {
        mSpaceClickListener = onSpaceClickListener;
    }


}
