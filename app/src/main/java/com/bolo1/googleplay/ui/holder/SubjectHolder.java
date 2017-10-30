package com.bolo1.googleplay.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.domain.SubjectInfo;
import com.bolo1.googleplay.ui.http.HttpHelper;
import com.bolo1.googleplay.ui.http.protocol.SubjectProtocol;
import com.bolo1.googleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by 菠萝 on 2017/10/27.
 */

public class SubjectHolder extends BaseHolder<SubjectInfo> {

    private TextView tv_subject_des;
    private ImageView iv_subject_image;
    private BitmapUtils mBitmapUtils;

    @Override
    public View initView() {
         View  view =UIUtils.getinflate(R.layout.subject_list_item);
        tv_subject_des = (TextView) view.findViewById(R.id.tv_subject_des);
        iv_subject_image = (ImageView) view.findViewById(R.id.iv_subject_image);
        mBitmapUtils = new BitmapUtils(UIUtils.getContext());
        return view;
    }

    @Override
    public void refreshView(SubjectInfo data) {
        tv_subject_des.setText(data.des);
        mBitmapUtils.display(iv_subject_image, HttpHelper.URL+"image?name="+data.url);
    }
}
