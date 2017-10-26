package com.bolo1.googleplay.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.adapter.MyBaseAdapter;
import com.bolo1.googleplay.ui.holder.BaseHolder;
import com.bolo1.googleplay.ui.holder.HomeHolder;
import com.bolo1.googleplay.ui.http.protocol.HomeProtocol;
import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public class HomeFragment extends BaseFragment {


    //    private ArrayList<String> data;

    private static final String tag = "HomeFragment";
    private ArrayList<AppInfo> data;

    @Override
    public View onCreateSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        LogUtils.d("创建成功的布局");
        listView.setAdapter(new HomeAdapter(data));
        return listView;
    }

    @Override
    public LoadingPage.ResultState initData() {
        HomeProtocol homeProtocol = new HomeProtocol();
        //获取第一页数据
        data = homeProtocol.getData(0);
        LogUtils.d("第一页的数据"+ data);
        return check(data);
    }


    private class HomeAdapter extends MyBaseAdapter<AppInfo> {
        public HomeAdapter(ArrayList<AppInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> getHolder() {
            return new HomeHolder();
        }

        @Override
        public ArrayList<AppInfo> onLoadMore() {
            HomeProtocol protocal = new HomeProtocol();
            ArrayList<AppInfo> moreData = protocal.getData(getListSize());
            return moreData;
        }
    }

}
