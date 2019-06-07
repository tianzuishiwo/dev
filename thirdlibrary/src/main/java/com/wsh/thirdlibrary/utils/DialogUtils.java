package com.wsh.thirdlibrary.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wsh.thirdlibrary.dialog.GowildBaseDialog;
import com.wsh.thirdlibrary.dialog.GowildConfirmDialog;
import com.wsh.thirdlibrary.dialog.GowildCustomDialog;
import com.wsh.thirdlibrary.dialog.model.DialogInfo;
import com.wsh.thirdlibrary.dialog.model.DialogType;

/**
 * Created by zy on 2017/11/6.
 */

public class DialogUtils {

    public static GowildBaseDialog showDialog(FragmentManager fragmentManager, DialogInfo info) {

        if (null != fragmentManager.findFragmentByTag(info.getTag())) {
            ((GowildBaseDialog) fragmentManager.findFragmentByTag(info.getTag())).dismissSelf();
        }

        GowildBaseDialog dialog = null;
        if (info != null) {
            Bundle pBundle = new Bundle();
            pBundle.putSerializable(GowildBaseDialog.TAG, info.dialogBuildInfo);
            DialogType type = info.getDialogType();
            switch (type) {
                case SINGLE:
                    break;
                case CUSTOMER:
                    dialog = GowildCustomDialog.getInstance(pBundle);
                    ((GowildCustomDialog) dialog).setCustomCallback(info.getCustomCallback());
                    break;
                case CONFIRM:
                    dialog = GowildConfirmDialog.getInstance(pBundle);
                    ((GowildConfirmDialog) dialog).setConfirmCallback(info.getConfirmCallback());
                    break;
                case PROGRESS:
                    break;
            }
        }
        if (dialog != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(dialog, info.getTag());
            fragmentTransaction.commitAllowingStateLoss();
        }

        return dialog;
    }

}
