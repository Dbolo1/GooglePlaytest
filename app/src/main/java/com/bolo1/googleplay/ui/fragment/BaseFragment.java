package com.bolo1.googleplay.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment {

    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(UIUtils.getContext()) {
            @Override
            public View onCreateSuccessView() {
                return BaseFragment.this.onCreateSuccessView();
            }
            @Override
            public ResultState onLoad() {
                return BaseFragment.this.initData();
            }
        };
        return loadingPage;
    }
    public abstract View onCreateSuccessView();
    public abstract LoadingPage.ResultState initData();
    public void loadData(){
        if(loadingPage!=null){
            loadingPage.onLoadData();
        }
    }



    public LoadingPage.ResultState check(Object object) {
        if (object != null) {
            if(object instanceof  ArrayList) {
                ArrayList list = (ArrayList) object;
                if (list.isEmpty()) {
                    return LoadingPage.ResultState.STATE_EMPTY;
                }else{
                    return LoadingPage.ResultState.STATE_SUCCESS;
                }
            }
        }
        return LoadingPage.ResultState.STATE_ERROR;
    }
}
