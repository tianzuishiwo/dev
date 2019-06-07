package com.wsh.thirdlibrary.dialog.model;

import android.text.Spannable;
import android.view.Gravity;

import com.wsh.thirdlibrary.dialog.GowildConfirmDialog;
import com.wsh.thirdlibrary.dialog.GowildCustomDialog;

import java.io.Serializable;

//import mobileclient.gowild.com.platformlibrary.dialog.GowildConfirmDialog;
//import mobileclient.gowild.com.platformlibrary.dialog.GowildCustomDialog;

/**
 * Created by zy on 2017/11/3.
 */

public class DialogInfo implements Serializable {

    public DialogBuildInfo dialogBuildInfo;

    public DialogInfo(DialogBuildInfo dialogBuildInfo) {
        this.dialogBuildInfo = dialogBuildInfo;
    }

    public String getTitle() {
        return dialogBuildInfo.title;
    }

    public String getContent() {
        return dialogBuildInfo.content;
    }

    public Spannable getSpannableContent() {
        return dialogBuildInfo.spannableContent;
    }

    public DialogType getDialogType() {
        return dialogBuildInfo.dialogType;
    }

    public int getType() {
        return dialogBuildInfo.type;
    }

    public String getPositiveText() {
        return dialogBuildInfo.positiveText;
    }

    public String getNegativeText() {
        return dialogBuildInfo.negativeText;
    }

    public int getSingleText() {
        return dialogBuildInfo.singleText;
    }

    public Object getCallbackData() {
        return dialogBuildInfo.callbackData;
    }

    public String getTag() {
        return dialogBuildInfo.tag;
    }

    public boolean isBackAble() {
        return dialogBuildInfo.isBackAble;
    }

    public boolean isSpaceAble() {
        return dialogBuildInfo.isSpaceAble;
    }

    public int getGravity() {
        return dialogBuildInfo.gravity;
    }

    public boolean isTitleVisible() {
        return dialogBuildInfo.titleVisible;
    }

    public boolean isMoveMent() {
        return dialogBuildInfo.moveMent;
    }


    public GowildCustomDialog.CustomCallback getCustomCallback() {
        return dialogBuildInfo.customCallback;
    }

    public GowildConfirmDialog.ConfirmCallback getConfirmCallback() {
        return dialogBuildInfo.confirmCallback;
    }

    public boolean isCancelButtonVisible() {
        return dialogBuildInfo.isCancelButtonVisible();
    }

    public int getTextGravity() {
        return dialogBuildInfo.textGravity;
    }

    public static class DialogBuildInfo implements Serializable {

        /**
         * 标题
         */
        private String title;

        /**
         * 内容
         */
        private String content;

        /**
         * 内容
         */
        private Spannable spannableContent;

        /**
         * 弹出框类型
         */
        private DialogType dialogType = DialogType.CONFIRM;

        /**
         * type code
         */
        private int type;

        /**
         * 确认键文字
         */
        private String positiveText;

        /**
         * 取消键文字
         */
        private String negativeText;

        /**
         * 单键文字
         */
        private int singleText;

        /**
         * callbackData
         */
        private Object callbackData;

        /**
         * tag
         */
        private String tag = "";

        /**
         * back可点（默认可点）
         */
        private boolean isBackAble = true;

        /**
         * 空白可点（默认可点）
         */
        private boolean isSpaceAble = true;

        /**
         * 位置
         */
        private int gravity = Gravity.CENTER;

        /**
         * 标题栏 是否显示
         */
        private boolean titleVisible = false;

        /**
         * 取消按钮是否显示
         */
        private boolean isCancelButtonVisible = true;

        /**
         * 超链接是否可点击
         */
        private boolean moveMent = false;

        private transient GowildCustomDialog.CustomCallback customCallback;

        private transient GowildConfirmDialog.ConfirmCallback confirmCallback;

        /**
         * 正文字体方向
         */
        private int textGravity = Gravity.CENTER;

        public DialogBuildInfo() {

        }

        public int getTextGravity() {
            return textGravity;
        }

        public DialogBuildInfo setTitle(String title) {
            this.title = title;
            return this;
        }

        public DialogBuildInfo setContent(String content) {
            this.content = content;
            return this;
        }

        public DialogBuildInfo setSpannableContent(Spannable spannableContent) {
            this.spannableContent = spannableContent;
            return this;
        }

        public DialogBuildInfo setDialogType(DialogType dialogType) {
            this.dialogType = dialogType;
            return this;
        }

        public DialogBuildInfo setType(int type) {
            this.type = type;
            return this;
        }

        public DialogBuildInfo setPositiveText(String positiveText) {
            this.positiveText = positiveText;
            return this;
        }

        public DialogBuildInfo setNegativeText(String negativeText) {
            this.negativeText = negativeText;
            return this;
        }

        public DialogBuildInfo setCallbackData(Object callbackData) {
            this.callbackData = callbackData;
            return this;
        }

        public DialogBuildInfo setSingleText(int singleText) {
            this.singleText = singleText;
            return this;
        }

        public DialogBuildInfo setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public DialogBuildInfo setBackAble(boolean backAble) {
            isBackAble = backAble;
            return this;
        }

        public DialogBuildInfo setSpaceAble(boolean spaceAble) {
            isSpaceAble = spaceAble;
            return this;
        }

        public DialogBuildInfo setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public DialogBuildInfo setTitleVisible(boolean titleVisible) {
            this.titleVisible = titleVisible;
            return this;
        }

        public DialogBuildInfo setMoveMent(boolean moveMent) {
            this.moveMent = moveMent;
            return this;
        }

        public DialogBuildInfo setCustomCallback(GowildCustomDialog.CustomCallback customCallback) {
            this.customCallback = customCallback;
            return this;
        }

        public DialogBuildInfo setConfirmCallback(GowildConfirmDialog.ConfirmCallback confirmCallback) {
            this.confirmCallback = confirmCallback;
            return this;
        }

        public DialogInfo create() {
            return new DialogInfo(this);
        }

        public boolean isCancelButtonVisible() {
            return isCancelButtonVisible;
        }

        public DialogBuildInfo setCancelButtonVisible(boolean cancelButtonVisible) {
            isCancelButtonVisible = cancelButtonVisible;
            return this;
        }

        public DialogBuildInfo setTextGravity(int textGravity) {
            this.textGravity = textGravity;
            return this;
        }
    }
}
