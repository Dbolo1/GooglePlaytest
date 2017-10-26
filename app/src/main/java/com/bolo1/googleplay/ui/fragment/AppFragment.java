package com.bolo1.googleplay.ui.fragment;

import android.view.View;

import com.bolo1.googleplay.ui.view.LoadingPage;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public  class AppFragment extends BaseFragment{
    @Override
    public View onCreateSuccessView() {
        return null;
    }

    @Override
    public LoadingPage.ResultState initData() {
        return LoadingPage.ResultState.STATE_ERROR;
    }


}
