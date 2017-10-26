package com.bolo1.googleplay.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.bolo1.googleplay.ui.holder.BaseHolder;
import com.bolo1.googleplay.ui.holder.MoreHolder;
import com.bolo1.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/18.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private ArrayList<T> data;
    public static int TYPE_MORE = 0;//加载更多数据
    public static int TYPE_NORMAL = 1;//正常布局

    public MyBaseAdapter(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size()+1;
    }


    //获得当前item的种类
    @Override
    public int getItemViewType(int position) {
        //判断位置
        if (position == getCount() - 1) {
            //加载更多数据
            return TYPE_MORE;
        } else {
            //交给子类去加载布局
            return getInnerType(position);
        }
    }

    //子类可以重写此方法
    public int getInnerType(int pos) {
        return TYPE_NORMAL;
    }


    //获得viewtype的种类
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder viewHolder;
        if (convertView == null) {
//            基类已完成findViewById，设置标记
//            判断是否加载更多数据
            if (getItemViewType(position) == TYPE_MORE) {
                viewHolder = new MoreHolder(hasMore());
            } else {
                viewHolder = getHolder();
            }
        } else {
            viewHolder = (BaseHolder) convertView.getTag();
        }
        if (getItemViewType(position) != TYPE_MORE) {
            viewHolder.setData(getItem(position));
        } else {
            //加载更多数据
            MoreHolder moreHolder = (MoreHolder) viewHolder;
            if (moreHolder.getData() == MoreHolder.STATE_MORE_MORE) {
                loadMore(moreHolder);
            }
        }
        return viewHolder.getmRootView();
    }

    //判断是否正在加载更多数据
    private boolean isLoadMore = false;


    //在此加载更多数据
    private void loadMore(final MoreHolder holder) {
        if (isLoadMore != true) {
            isLoadMore = true;

            new Thread() {
                @Override
                public void run() {
                    final ArrayList<T> moreData = onLoadMore();
                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (moreData != null) {
                                //需要加载更多数据，且数据大于20
                                if (moreData.size() < 20) {
                                    holder.setData(MoreHolder.STATE_MORE_NONE);
                                    Toast.makeText(UIUtils.getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                                } else {
                                    holder.setData(MoreHolder.STATE_MORE_MORE);
                                }
                                //将数据添加到集合中
                                data.addAll(moreData);
                                //更新ui
                                MyBaseAdapter.this.notifyDataSetChanged();
                            } else {
                                //加载失败
                                holder.setData(MoreHolder.STATE_MORE_ERROR);
                            }
                            isLoadMore = false;
                        }
                    });
                }
            }.start();
        }
    }

    //获取holder交给子类去实现
    public abstract BaseHolder<T> getHolder();

    //子类可以重写此方法判断是否由更多数据
    public boolean hasMore() {
        return true;
    }

    public abstract ArrayList<T> onLoadMore();
    //获取当前数据的大小
    public int getListSize(){
        return data.size();
    }
}
