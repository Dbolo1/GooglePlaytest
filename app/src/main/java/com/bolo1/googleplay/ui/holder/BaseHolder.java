package com.bolo1.googleplay.ui.holder;

import android.view.View;
import android.widget.BaseAdapter;

/**
 * Created by 菠萝 on 2017/10/18.
 */

public abstract class BaseHolder<T> {

    private final View mRootView;
    private T data;
    public BaseHolder() {
        mRootView = initView();
        //3，打标记
        mRootView.setTag(this);
    }
    //1 初始化布局,交给子类去写
    public abstract View initView();

    //获取根布局
    public View getmRootView() {
        return mRootView;
    }
    //设置数据
    public void setData(T data) {
        this.data = data;
        //设置数据的同时刷新布局文件
        refreshView(this.data);
    }
    //获取数据
    public T getData() {
        return data;
    }
    //加载布局文件的数据
    public abstract void refreshView(T data);

}
