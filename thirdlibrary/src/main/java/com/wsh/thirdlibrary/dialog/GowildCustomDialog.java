package com.wsh.thirdlibrary.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wsh.thirdlibrary.dialog.model.DialogInfo;

//import mobileclient.gowild.com.platformlibrary.dialog.model.DialogInfo;

/**
 * Created by zy on 2017/11/6.
 * 自己扩展的dialog
 */

public class GowildCustomDialog extends GowildBaseDialog {

    public CustomCallback customCallback;

    public static GowildCustomDialog getInstance(Bundle bundle) {
        GowildCustomDialog dialog = new GowildCustomDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    public GowildCustomDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogInfo.DialogBuildInfo build = (DialogInfo.DialogBuildInfo) bundle.getSerializable(TAG);
            DialogInfo info = null;
            if (null != build) {
                info = build.create();
            }
            if (info != null) {
                mDialogTag = info.getTag();
            }
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View contentView = null;
        if (customCallback != null) {
            contentView = customCallback.getContentView(mDialogTag);
        } else {
            throw new IllegalArgumentException("must be set customCallback");
        }
        FrameLayout layout = new FrameLayout(getActivity());
        layout.setClickable(true);
        layout.setOnClickListener(mSpaceClickListener);
        if (contentView != null && contentView.getLayoutParams() != null) {
            layout.addView(contentView, contentView.getLayoutParams());
            contentView.setClickable(true);
        }else if(contentView != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            contentView.setLayoutParams(layoutParams);
            layout.addView(contentView,contentView.getLayoutParams());
            contentView.setClickable(true);
        }
        if(info != null) {
            setCancelable(info.isBackAble());
        }

        return layout;
    }

    public void setCustomCallback(CustomCallback customCallback) {
        this.customCallback = customCallback;
    }

    public interface CustomCallback {
        View getContentView(String tag);
    }

}
