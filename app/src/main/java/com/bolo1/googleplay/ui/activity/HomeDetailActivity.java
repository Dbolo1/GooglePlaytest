package com.bolo1.googleplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.holder.DetailAppInfoHolder;
import com.bolo1.googleplay.ui.holder.DetailSafeHolder;
import com.bolo1.googleplay.ui.http.protocol.HomeDetailProtocol;
import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.UIUtils;

/**
 * Created by 菠萝 on 2017/10/30.
 */

public class HomeDetailActivity extends BaseActivity {

    private AppInfo data;
    private String mPackageName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadingPage loadingPage = new LoadingPage(UIUtils.getContext()) {
            @Override
            public View onCreateSuccessView() {
                return HomeDetailActivity.this.onCreateSuccessView();
            }

            @Override
            public ResultState onLoad() {
                return HomeDetailActivity.this.onLoad();
            }
        };

        setContentView(loadingPage);
        //开始加载网络
        loadingPage.onLoadData();
        //获取首页传递应用的包名
        mPackageName = getIntent().getStringExtra("PackageName");
}

    private View onCreateSuccessView() {

        //初始化布局
        View view=UIUtils.getinflate(R.layout.pager_home_detail);
        //初始化frameLayout 动态的向里面添加布局
        FrameLayout fl_detail_appinfo = (FrameLayout) view.findViewById(R.id.fl_detail_appinfo);
        //添加app信息头部
        DetailAppInfoHolder detailHolder = new DetailAppInfoHolder();
        fl_detail_appinfo.addView(detailHolder.getmRootView());
        detailHolder.setData(data);

        //添加安全信息
        FrameLayout fl_detail_safeinfo = (FrameLayout) view.findViewById(R.id.fl_detail_safeinfo);

        DetailSafeHolder safeHolder = new DetailSafeHolder();
        fl_detail_safeinfo.addView(safeHolder.getmRootView());
        safeHolder.setData(data);






        return view;

    }

    private LoadingPage.ResultState onLoad() {

        HomeDetailProtocol protocol = new HomeDetailProtocol(mPackageName);
        data = protocol.getData(0);
        LogUtils.d("应用详情页解析返回的数组data是否为空=============="+data);
        if(data !=null){
            return LoadingPage.ResultState.STATE_SUCCESS;
        }else{
            return LoadingPage.ResultState.STATE_ERROR;
        }

    }
}
