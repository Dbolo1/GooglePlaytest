package com.bolo1.googleplay.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;



/**
 * Created by 菠萝 on 2017/10/28.
 */

public class DrawableUtils {
    public static GradientDrawable setGradientDrawable(int color, int radius ){
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }
    public static StateListDrawable setSelector(Drawable normal,Drawable press){
        StateListDrawable  stateListDrawable=new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},press);
        stateListDrawable.addState(new int[]{},normal);
        return stateListDrawable;
    }
    //重载
    public static StateListDrawable setSelector(int normal,int  press,int radius){
//        GradientDrawable bgNormal = DrawableUtils.setGradientDrawable(color, UIUtils.dip2px(6));
//        GradientDrawable bgPress = DrawableUtils.setGradientDrawable(Color.rgb(r, g, b), UIUtils.dip2px(6));
//        StateListDrawable stateListDrawable=DrawableUtils.setSelector(bgNormal,bgPress);
        GradientDrawable bgNormal = setGradientDrawable(normal, radius);
        GradientDrawable bgPress = setGradientDrawable(press, radius);
        StateListDrawable stateListDrawable = setSelector(bgNormal, bgPress);
        return stateListDrawable;
    }


}
