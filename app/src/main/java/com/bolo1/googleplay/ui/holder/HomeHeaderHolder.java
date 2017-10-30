package com.bolo1.googleplay.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.http.HttpHelper;
import com.bolo1.googleplay.utils.BitmapHelper;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/30.
 */

public class HomeHeaderHolder extends BaseHolder<ArrayList<String>> {

    private ViewPager mViewPager;
    private ArrayList<String> mPicList;
    private LinearLayout indicator;
    private int prePosition;

    @Override
    public View initView() {
        //添加头布局
        RelativeLayout rlroot = new RelativeLayout(UIUtils.getContext());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, UIUtils.dip2px(150));
        rlroot.setLayoutParams(params);

        //设置viewpager
        mViewPager = new ViewPager(UIUtils.getContext());
        RelativeLayout.LayoutParams viewpagerParams = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mViewPager.setLayoutParams(viewpagerParams);

        //设置指示器
        indicator = new LinearLayout(UIUtils.getContext());
        RelativeLayout.LayoutParams params1 = new RelativeLayout.
                LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置指示器位置
        params1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        indicator.setLayoutParams(params1);
        //对指示器设置padding
        int padding = UIUtils.dip2px(5);
        indicator.setPadding(padding, padding, padding, padding);
        // 同步指示器位置

        rlroot.addView(mViewPager);
        rlroot.addView(indicator);

        return rlroot;
    }

    @Override
    public void refreshView(ArrayList<String> data) {
        mPicList = data;
        //获取数据后再设置adapter
        mViewPager.setAdapter(new HeaderView());
        mViewPager.setCurrentItem(mPicList.size() * 10000);
        //在此，初始化指示器布局
        indicator.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //为指示器设置图片
        for (int i = 0; i < mPicList.size(); i++) {
            ImageView imageView = new ImageView(UIUtils.getContext());
            if (i == 0) {
                //默认第一个指示器图片为白色
                imageView.setImageResource(R.drawable.indicator_selected);
            } else {
                imageView.setImageResource(R.drawable.indicator_normal);
                params.leftMargin = UIUtils.dip2px(3);
            }
            indicator.addView(imageView, params);
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position = position % HomeHeaderHolder.this.mPicList.size();
                //获取当前位置的指示器
                ImageView pos = (ImageView) indicator.getChildAt(position);
                pos.setImageResource(R.drawable.indicator_selected);

                //将上一个点设置为黑色
                ImageView preImage = (ImageView) indicator.getChildAt(prePosition);
                preImage.setImageResource(R.drawable.indicator_normal);

                prePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        HomeHeaderTask task = new HomeHeaderTask();
        task.start();
    }

    public class HomeHeaderTask implements Runnable {

        public void start() {
            //handler
            UIUtils.getHandler().removeCallbacksAndMessages(null);
            UIUtils.getHandler().postDelayed(this, 3000);
        }

        @Override
        public void run() {
            int CurrentItem = mViewPager.getCurrentItem();
            CurrentItem++;
            mViewPager.setCurrentItem(CurrentItem);
            UIUtils.getHandler().postDelayed(this, 3000);
        }
    }

    private class HeaderView extends PagerAdapter {

        private final BitmapUtils mBitmapUtils;

        private HeaderView() {
            mBitmapUtils = BitmapHelper.getBitmapUtils();
            mBitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_default);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //对位置取余
            position = position % mPicList.size();
            //初始化item
            ImageView imageView = new ImageView(UIUtils.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mBitmapUtils.display(imageView, HttpHelper.URL + "image?name=" + mPicList.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //销毁item
            container.removeView((View) object);
        }
    }
}
