package com.wsh.androidstudydemo.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //触发每个子view的onMeasure方法
        measureChildren(widthMeasureSpec,heightMeasureSpec);// measureChild()测量单个view

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int heigthSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();

        if (childCount==0){
            setMeasuredDimension(0,0);
        }else {
            if (widthMode==MeasureSpec.AT_MOST&&heigthMode==MeasureSpec.AT_MOST){
                setMeasuredDimension(getMaxWithFromChildren(),getTotalHeigth());
            }else if (widthMode==MeasureSpec.AT_MOST){
                setMeasuredDimension(getMaxWithFromChildren(),heigthSize);
            }else if (heigthMode==MeasureSpec.AT_MOST){
                setMeasuredDimension(widthSize,getTotalHeigth());
            }
        }

    }

    private int getTotalHeigth() {
        int totalHeigth = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            totalHeigth = totalHeigth+getChildAt(i).getMeasuredHeight();
        }
        return totalHeigth;
    }

    private int getMaxWithFromChildren() {
        int maxWidth = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount ; i++) {
            int measuredWidth = getChildAt(i).getMeasuredWidth();
            if (measuredWidth>maxWidth){
                maxWidth=measuredWidth;
            }
        }
        return maxWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int curHeigth = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            child.layout(l,curHeigth,l+measuredWidth,curHeigth+measuredHeight);
            curHeigth+=measuredHeight;
        }
    }























}
