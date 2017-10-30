package com.bolo1.googleplay.ui.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.http.HttpHelper;
import com.bolo1.googleplay.utils.BitmapHelper;
import com.bolo1.googleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.text.Format;

/**
 * Created by 菠萝 on 2017/10/30.
 */

public class DetailAppInfoHolder extends BaseHolder<AppInfo> {

    private ImageView iv_appinfo_icon;
    private RatingBar rb_appinfo_stars;
    private TextView tv_appinfo_name, tv_appinfo_downloadnum, tv_appinfo_version, tv_appinfo_date, tv_appinfo_size;
    private BitmapUtils mBitmapUitl;

    @Override
    public View initView() {

        View view = UIUtils.getinflate(R.layout.layout_detail_appinfo);
        iv_appinfo_icon = (ImageView) view.findViewById(R.id.iv_appinfo_icon);
        tv_appinfo_name = (TextView) view.findViewById(R.id.tv_appinfo_name);
        rb_appinfo_stars = (RatingBar) view.findViewById(R.id.rb_appinfo_stars);
        tv_appinfo_downloadnum = (TextView) view.findViewById(R.id.tv_appinfo_downloadnum);
        tv_appinfo_version = (TextView) view.findViewById(R.id.tv_appinfo_version);
        tv_appinfo_date = (TextView) view.findViewById(R.id.tv_appinfo_date);
        tv_appinfo_size = (TextView) view.findViewById(R.id.tv_appinfo_size);
        mBitmapUitl = BitmapHelper.getBitmapUtils();
        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        //设置数据
        mBitmapUitl.display(iv_appinfo_icon, HttpHelper.URL + "image?name=" + data.iconUrl);
        tv_appinfo_name.setText(data.name);
        rb_appinfo_stars.setRating((float) data.stars);
        tv_appinfo_downloadnum.setText("下载量: " + data.downloadNum);
        tv_appinfo_version.setText("版本号: " + data.version);
        tv_appinfo_date.setText("更新时间: " + data.date);
        tv_appinfo_size.setText("应用大小: " + Formatter.formatFileSize(UIUtils.getContext(), data.size));


    }
}
