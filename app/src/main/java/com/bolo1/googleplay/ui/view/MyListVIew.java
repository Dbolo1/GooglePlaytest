package com.bolo1.googleplay.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

import com.bolo1.googleplay.utils.UIUtils;

/**
 * Created by 菠萝 on 2017/10/27.
 */

public class MyListVIew extends ListView {
    public MyListVIew(Context context) {
        super(context);
        initView();
    }
    public MyListVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyListVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView() {
        this.setSelector(new ColorDrawable());//设置选择时的颜色为透明色
        this.setCacheColorHint(Color.TRANSPARENT); //设置listview的默认颜色为透明色
        this.setDivider(null); //去除listviewItem之间的空隙

    }
}
