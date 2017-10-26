package com.bolo1.googleplay.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.utils.UIUtils;

/**
 * Created by 菠萝 on 2017/10/18.
 */

public class HomeHolder extends BaseHolder<AppInfo> {

    private TextView tvContent;


    @Override
    public View initView() {
        View view = UIUtils.getinflate(R.layout.testview);
        tvContent = (TextView) view.findViewById(R.id.test_textview);
        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        tvContent.setText(data.name);
    }
}
