package com.bolo1.googleplay.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.domain.CategoryInfo;
import com.bolo1.googleplay.ui.http.HttpHelper;
import com.bolo1.googleplay.utils.BitmapHelper;
import com.bolo1.googleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/28.
 */

public class CategoryHolder extends BaseHolder<CategoryInfo> implements View.OnClickListener {

    private LinearLayout ll_grid1,ll_grid2,ll_grid3;
    private ImageView iv_category_icon1,iv_category_icon2,iv_category_icon3;
    private TextView tv_category_name1,tv_category_name2,tv_category_name3;
    private BitmapUtils mBitmapHelper;

    @Override
    public View initView() {
        View view=UIUtils.getinflate(R.layout.list_item_category);
        ll_grid1 = (LinearLayout) view.findViewById(R.id.ll_grid1);
        ll_grid2 = (LinearLayout) view.findViewById(R.id.ll_grid2);
        ll_grid3 = (LinearLayout) view.findViewById(R.id.ll_grid3);

        iv_category_icon1 = (ImageView) view.findViewById(R.id.iv_category_icon1);
        iv_category_icon2 = (ImageView) view.findViewById(R.id.iv_category_icon2);
        iv_category_icon3 = (ImageView) view.findViewById(R.id.iv_category_icon3);

        tv_category_name1 = (TextView) view.findViewById(R.id.tv_category_name1);
        tv_category_name2 = (TextView) view.findViewById(R.id.tv_category_name2);
        tv_category_name3 = (TextView) view.findViewById(R.id.tv_category_name3);


        ll_grid1.setOnClickListener(this);
        ll_grid2.setOnClickListener(this);
        ll_grid3.setOnClickListener(this);

        mBitmapHelper = BitmapHelper.getBitmapUtils();
        return view;
    }

    @Override
    public void refreshView(CategoryInfo data) {
        tv_category_name1.setText(data.name1);
        tv_category_name2.setText(data.name2);
        tv_category_name3.setText(data.name3);

        mBitmapHelper.display(iv_category_icon1, HttpHelper.URL+"image?name="+data.url1);
        mBitmapHelper.display(iv_category_icon2, HttpHelper.URL+"image?name="+data.url2);
        mBitmapHelper.display(iv_category_icon3, HttpHelper.URL+"image?name="+data.url3);
    }

    @Override
    public void onClick(View v) {

    }
}
