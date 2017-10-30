package com.bolo1.googleplay.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.utils.UIUtils;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public  class GameFragment extends BaseFragment{
    @Override
    public View onCreateSuccessView() {

        return null;
    }
    @Override
    public LoadingPage.ResultState initData() {
        return LoadingPage.ResultState.STATE_EMPTY;
    }
}
