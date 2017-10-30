package com.bolo1.googleplay.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.activity.HomeDetailActivity;
import com.bolo1.googleplay.ui.adapter.MyBaseAdapter;
import com.bolo1.googleplay.ui.holder.BaseHolder;
import com.bolo1.googleplay.ui.holder.HomeHeaderHolder;
import com.bolo1.googleplay.ui.holder.HomeHolder;
import com.bolo1.googleplay.ui.http.protocol.HomeProtocol;
import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.ui.view.MyListVIew;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public class HomeFragment extends BaseFragment {


    private static final String tag = "HomeFragment";
    private ArrayList<AppInfo> data;
    private ArrayList<String> mmPicList = new ArrayList<String>();

    @Override
    public View onCreateSuccessView() {
        MyListVIew listView = new MyListVIew(UIUtils.getContext());
        LogUtils.d("创建成功的布局HomeFragment");
        HomeHeaderHolder header = new HomeHeaderHolder();
        LogUtils.d("首页轮播广告数据=================================" + mmPicList);
        header.setData(mmPicList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo info = data.get(position-1);
                Intent intent = new Intent(UIUtils.getContext(), HomeDetailActivity.class);
                LogUtils.d("应用详情页传递的包名是否为空======"+info.packageName);
                intent.putExtra("PackageName",info.packageName);
                startActivity(intent);
            }
        });
        listView.addHeaderView(header.getmRootView());
        listView.setAdapter(new HomeAdapter(data));
        return listView;
    }

    @Override
    public LoadingPage.ResultState initData() {
        HomeProtocol homeProtocol = new HomeProtocol();
        //获取第一页数据
        data = homeProtocol.getData(0);
        mmPicList = homeProtocol.getPicList();
        LogUtils.d("轮播条解析的数据是否为空================" + mmPicList);

        LogUtils.d("第一页的数据" + data);
        return check(data);
    }


    private class HomeAdapter extends MyBaseAdapter<AppInfo> {
        public HomeAdapter(ArrayList<AppInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> getHolder(int position) {

            return new HomeHolder();
        }

        @Override
        public ArrayList<AppInfo> onLoadMore() {
            HomeProtocol protocol = new HomeProtocol();
            ArrayList<AppInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }
    }

}
