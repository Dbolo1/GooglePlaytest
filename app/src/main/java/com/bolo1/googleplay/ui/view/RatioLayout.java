package com.bolo1.googleplay.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.utils.LogUtils;

/**
 * Created by 菠萝 on 2017/10/27.
 */

public class RatioLayout extends FrameLayout {

    private float ratio;

    public RatioLayout(@NonNull Context context) {
        this(context,null);

    }

    public RatioLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public RatioLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);
        ratio = typedArray.getFloat(R.styleable.RatioLayout_ratio, -1);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        LogUtils.d("控件的宽度是"+width+"控件宽度的模式是"+widthMode+"控件的高度是"+height+"控件高度的模式是"+heightMode);

        //  宽度要确定,高度不确定
        if (ratio > 0 && widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
            //获取实际图片的宽度
            int imageWidth = width - getPaddingLeft() - getPaddingRight();
            int imageHeight = (int) (imageWidth / ratio + 0.5f);
            height = imageHeight + getPaddingTop() + getPaddingBottom();
            //重新给image大小赋值
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
