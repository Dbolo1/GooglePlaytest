package com.bolo1.googleplay.ui.holder;


import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.http.HttpHelper;
import com.bolo1.googleplay.utils.BitmapHelper;
import com.bolo1.googleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by 菠萝 on 2017/10/18.
 */

public class HomeHolder extends BaseHolder<AppInfo> {

    private TextView tv_apply_name,tv_apply_des,tv_apply_size;
    private ImageView iv_download_icon;
    private BitmapUtils mBitmapUtils;
    private ImageView iv_icon;
    private RatingBar rb_apply_rating;


    @Override
    public View initView() {
        View view = UIUtils.getinflate(R.layout.testview);
        tv_apply_name = (TextView) view.findViewById(R.id.tv_apply_name);
        tv_apply_des = (TextView) view.findViewById(R.id.tv_apply_des);
        tv_apply_size = (TextView) view.findViewById(R.id.tv_apply_size);
        iv_download_icon = (ImageView) view.findViewById(R.id.iv_download_icon);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        rb_apply_rating = (RatingBar) view.findViewById(R.id.rb_apply_rating);

        mBitmapUtils = BitmapHelper.getBitmapUtils();

        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        tv_apply_name.setText(data.name);
        tv_apply_des.setText(data.des);
        tv_apply_size.setText(Formatter.formatFileSize(UIUtils.getContext(),data.size));
        rb_apply_rating.setRating((float) data.stars);
        mBitmapUtils.display(iv_icon,HttpHelper.URL+"image?name="+data.iconUrl);
    }
}
