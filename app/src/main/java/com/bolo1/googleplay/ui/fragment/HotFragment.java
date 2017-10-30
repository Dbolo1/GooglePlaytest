package com.bolo1.googleplay.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bolo1.googleplay.ui.http.protocol.HotProtocol;
import com.bolo1.googleplay.ui.view.FlowLayout;
import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.utils.DrawableUtils;
import com.bolo1.googleplay.utils.UIUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public class HotFragment extends BaseFragment {

    private ArrayList<String> data;
    private int padding;

    @Override
    public View onCreateSuccessView() {
        ScrollView scrollView = new ScrollView(UIUtils.getContext());
        FlowLayout flowLayout = new FlowLayout(UIUtils.getContext());

        padding = UIUtils.dip2px(10);
        flowLayout.setPadding(padding, padding, padding, padding);
        flowLayout.setHorizontalSpacing(6);
        flowLayout.setVerticalSpacing(8);

        for (int i = 0; i < data.size(); i++) {
            TextView textView = new TextView(UIUtils.getContext());
            final String keyword = data.get(i);
            //调整字体样式
            textView.setText(keyword);
            textView.setPadding(padding,padding, padding, padding);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
            textView.setGravity(Gravity.CENTER);

            Random random=new Random();
            int r=30+random.nextInt(200);
            int g=30+random.nextInt(200);
            int b=30+random.nextInt(200);

            int color = 0xffcecece;//灰色


//            GradientDrawable bgNormal = DrawableUtils.setGradientDrawable(color, UIUtils.dip2px(6));
//            GradientDrawable bgPress = DrawableUtils.setGradientDrawable(Color.rgb(r, g, b), UIUtils.dip2px(6));
//            StateListDrawable stateListDrawable=DrawableUtils.setSelector(bgNormal,bgPress);
//
            StateListDrawable stateListDrawable = DrawableUtils.setSelector(Color.rgb(r, g, b), color, UIUtils.dip2px(6));
            textView.setBackgroundDrawable(stateListDrawable);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(),keyword,Toast.LENGTH_SHORT).show();
                }
            });
            flowLayout.addView(textView);
        }
        scrollView.addView(flowLayout);
        return scrollView;
    }

    @Override
    public LoadingPage.ResultState initData() {
        HotProtocol protocol = new HotProtocol();
        data = protocol.getData(0);
        return check(data);
    }
}
