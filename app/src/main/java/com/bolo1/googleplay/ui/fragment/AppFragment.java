package com.bolo1.googleplay.ui.fragment;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.adapter.MyBaseAdapter;
import com.bolo1.googleplay.ui.holder.AppHolder;
import com.bolo1.googleplay.ui.holder.BaseHolder;
import com.bolo1.googleplay.ui.http.protocol.AppProtocol;
import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.ui.view.MyListVIew;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public  class AppFragment extends BaseFragment{


    private ArrayList<AppInfo> data;

    @Override
    public View onCreateSuccessView() {
        MyListVIew listView = new MyListVIew(UIUtils.getContext());
        listView.setAdapter(new AppAdapter(data));
        return listView;


    }

    @Override
    public LoadingPage.ResultState initData() {
        AppProtocol protocol = new AppProtocol();
        data = protocol.getData(0);
        return check(data);

    }


    private class AppAdapter extends MyBaseAdapter{
        public AppAdapter(ArrayList data) {
            super(data);
        }

        @Override
        public BaseHolder getHolder(int position) {
            return new AppHolder();
        }
        @Override
        public ArrayList onLoadMore() {
            AppProtocol  protocol = new AppProtocol();
            ArrayList<AppInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }
    }
}
