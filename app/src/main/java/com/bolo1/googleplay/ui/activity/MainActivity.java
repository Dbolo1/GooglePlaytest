package com.bolo1.googleplay.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.ui.fragment.BaseFragment;
import com.bolo1.googleplay.ui.fragment.FragmentFactory;
import com.bolo1.googleplay.ui.view.PagerTab;
import com.bolo1.googleplay.utils.UIUtils;

public class MainActivity extends BaseActivity {

    private ViewPager viewpager;
    private PagerTab pager_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        pager_tab = (PagerTab) findViewById(R.id.pager_tab);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        viewpager.setAdapter(myAdapter);
        pager_tab.setViewPager(viewpager);
        pager_tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                BaseFragment fragment = FragmentFactory.onCreateFragment(position);
                fragment.loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });



    }

    class MyAdapter extends FragmentPagerAdapter {

        private final String[] mTab_names;

        //初始化标签页标题
        public MyAdapter(FragmentManager fm) {
            super(fm);
            mTab_names = UIUtils.getStringArray(R.array.tab_names);
        }

        //获得当前标签页标题
        @Override
        public CharSequence getPageTitle(int position) {
            return mTab_names[position];
        }

        //获得当前位置的fragment
        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.onCreateFragment(position);
            return fragment;
        }

        //获得标签标题总数
        @Override
        public int getCount() {
            return mTab_names.length;
        }
    }
}
