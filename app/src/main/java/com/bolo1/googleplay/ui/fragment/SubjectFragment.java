package com.bolo1.googleplay.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.bolo1.googleplay.domain.SubjectInfo;
import com.bolo1.googleplay.ui.adapter.MyBaseAdapter;
import com.bolo1.googleplay.ui.holder.BaseHolder;
import com.bolo1.googleplay.ui.holder.SubjectHolder;
import com.bolo1.googleplay.ui.http.protocol.SubjectProtocol;
import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.ui.view.MyListVIew;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public  class SubjectFragment extends BaseFragment{

    private ArrayList<SubjectInfo> data;

    @Override
    public View onCreateSuccessView() {
        MyListVIew listVIew=new MyListVIew(UIUtils.getContext());
        listVIew.setAdapter(new SubjectAdapter(data));
        return listVIew;
    }

    @Override
    public LoadingPage.ResultState initData() {
        SubjectProtocol  protocol=new SubjectProtocol();
        data = protocol.getData(0);
        LogUtils.d("Subject解析第一页的数据"+data);
        return check(data);
    }

    private class SubjectAdapter  extends MyBaseAdapter<SubjectInfo>{

        public SubjectAdapter(ArrayList<SubjectInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<SubjectInfo> getHolder(int position) {
            return new SubjectHolder();
        }

        @Override
        public ArrayList<SubjectInfo> onLoadMore() {
            SubjectProtocol  protocol= new SubjectProtocol();
            ArrayList<SubjectInfo> moreData = protocol.getData(getListSize());

            return moreData;
        }
    }

}
