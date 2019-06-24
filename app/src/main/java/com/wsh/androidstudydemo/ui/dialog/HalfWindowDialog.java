package com.wsh.androidstudydemo.ui.dialog;

//import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.wsh.androidstudydemo.R;

public class HalfWindowDialog extends DialogFragment {

    public final static String KEY_DATA = "key_data";

    public static HalfWindowDialog newInstance(){
        return new HalfWindowDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        String data = (String) bundle.getString(KEY_DATA,"");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View roorView = inflater.inflate(R.layout.half_window, container, false);
        return roorView;
    }

    private boolean mBottom = true;
    public HalfWindowDialog setBottom(boolean bottom){
        mBottom=bottom;
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        getDialog().getWindow().setLayout(displayMetrics.widthPixels,getDialog().getWindow().getAttributes().height);


        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.gravity =mBottom? Gravity.BOTTOM:Gravity.TOP;
        getDialog().getWindow().setAttributes(layoutParams);
    }
}
