package com.wsh.androidstudydemo.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wsh.androidstudydemo.R;

/**
 *  正方形view
 *  自定义view文章
 *  https://www.jianshu.com/p/c84693096e41
 */
public class CircleView extends View {

    private int defaultSize;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        defaultSize = typedArray.getDimensionPixelSize(R.styleable.CircleView_default_size, 100);

        typedArray.recycle();//释放内存
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(defaultSize,widthMeasureSpec);
        int heigth = getMySize(defaultSize,heightMeasureSpec);

        if (width>heigth){
            width=heigth;
        }else {
            heigth=width;
        }

        // 设置当前view的最终尺寸
        setMeasuredDimension(width,heigth);
//        setMeasuredDimension(199,199);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int value = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.UNSPECIFIED://父容器没有对当前view有任何限制，当前view可以任意去尺寸
                value = defaultSize;
                break;
            case MeasureSpec.EXACTLY://当前的尺寸就是该view应该取得尺寸（match_parent 父view剩余空间）(100dp固定尺寸)
                value = size;
                break;
            case MeasureSpec.AT_MOST://当前尺寸是当前view能取的最大尺寸（wrap_parent 包裹view）
                value = size;
                break;
        }
        return value;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = getMeasuredWidth()/2;
        int centerX = radius;
        int centerY = radius;
//        int centerX = getLeft()+radius;
//        int centerY = getTop()+radius;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        canvas.drawCircle(centerX,centerY,radius,paint);
    }





}
