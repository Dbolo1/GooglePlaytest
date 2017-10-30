package com.bolo1.googleplay.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.domain.CategoryInfo;
import com.bolo1.googleplay.utils.UIUtils;


import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/29.
 */

public class TitleHolder extends BaseHolder<CategoryInfo> {

    private TextView textView;

    @Override
    public View initView() {
        View view =UIUtils.getinflate(R.layout.category_title_layout);
        textView = (TextView) view.findViewById(R.id.tv_category_title);

        return view;
    }

    @Override
    public void refreshView(CategoryInfo data) {
        textView.setText(data.title);
    }
}
