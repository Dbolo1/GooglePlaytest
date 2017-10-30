package com.bolo1.googleplay.ui.holder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.http.HttpHelper;
import com.bolo1.googleplay.utils.BitmapHelper;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/30.
 */

public class DetailSafeHolder extends BaseHolder<AppInfo> {
    private ImageView[] mSafeIcon;
    private LinearLayout[] mll_Des;
    private ImageView[] mDesIcon;
    private TextView[] mSafeDes;
    private BitmapUtils mBitmapUtils;
    private RelativeLayout rl_des_root;
    private LinearLayout ll_des_root;
    private int mDesHeight;
    private LinearLayout.LayoutParams mParams;
    private ImageView iv_arrow;

    @Override
    public View initView() {
        View view = UIUtils.getinflate(R.layout.layout_detail_safeinfo);
        mSafeIcon = new ImageView[4];
        mSafeIcon[0] = (ImageView) view.findViewById(R.id.iv_safe1);
        mSafeIcon[1] = (ImageView) view.findViewById(R.id.iv_safe2);
        mSafeIcon[2] = (ImageView) view.findViewById(R.id.iv_safe3);
        mSafeIcon[3] = (ImageView) view.findViewById(R.id.iv_safe4);

        mll_Des = new LinearLayout[4];
        mll_Des[0] = (LinearLayout) view.findViewById(R.id.ll_des1);
        mll_Des[1] = (LinearLayout) view.findViewById(R.id.ll_des2);
        mll_Des[2] = (LinearLayout) view.findViewById(R.id.ll_des3);
        mll_Des[3] = (LinearLayout) view.findViewById(R.id.ll_des4);

        mDesIcon = new ImageView[4];
        mDesIcon[0] = (ImageView) view.findViewById(R.id.iv_des1);
        mDesIcon[1] = (ImageView) view.findViewById(R.id.iv_des2);
        mDesIcon[2] = (ImageView) view.findViewById(R.id.iv_des3);
        mDesIcon[3] = (ImageView) view.findViewById(R.id.iv_des4);


        mSafeDes = new TextView[4];
        mSafeDes[0] = (TextView) view.findViewById(R.id.tv_des1);
        mSafeDes[1] = (TextView) view.findViewById(R.id.tv_des2);
        mSafeDes[2] = (TextView) view.findViewById(R.id.tv_des3);
        mSafeDes[3] = (TextView) view.findViewById(R.id.tv_des4);
        mBitmapUtils = BitmapHelper.getBitmapUtils();
        rl_des_root = (RelativeLayout) view.findViewById(R.id.rl_des_root);
        ll_des_root = (LinearLayout) view.findViewById(R.id.ll_des_root);
        iv_arrow = (ImageView) view.findViewById(R.id.iv_arrow);

        rl_des_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        return view;
    }

    private boolean isOpen = false;

    private void toggle() {
        ValueAnimator valueAnimator = null;
        if (isOpen) {
            isOpen = false;
            valueAnimator = ValueAnimator.ofInt(mDesHeight, 0);
        } else {
            isOpen = true;
            valueAnimator = ValueAnimator.ofInt(0, mDesHeight);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer height = (Integer) animation.getAnimatedValue();
                mParams.height = height;
                ll_des_root.setLayoutParams(mParams);
            }
        });
        //对动画的监听
         valueAnimator.addListener(new Animator.AnimatorListener() {
             @Override
             public void onAnimationStart(Animator animation) {

             }

             @Override
             public void onAnimationEnd(Animator animation) {
                //在动画的结束,改变小箭头的方向
                 if(isOpen){
                     iv_arrow.setImageResource(R.drawable.arrow_up);
                 }else{
                     iv_arrow.setImageResource(R.drawable.arrow_down);
                 }
             }

             @Override
             public void onAnimationCancel(Animator animation) {

             }

             @Override
             public void onAnimationRepeat(Animator animation) {

             }
         });




        valueAnimator.setDuration(200);
        valueAnimator.start();
    }

    @Override
    public void refreshView(AppInfo data) {
        ArrayList<AppInfo.SafeInfo> infos = data.safe;
        for (int i = 0; i < 4; i++) {
            if (i < infos.size()) {
                //为view赋值
                AppInfo.SafeInfo safeinfo = infos.get(i);
                mBitmapUtils.display(mSafeIcon[i], HttpHelper.URL + "image?name=" + safeinfo.safeUrl);
                mSafeDes[i].setText(safeinfo.safeDes);
                mBitmapUtils.display(mDesIcon[i], HttpHelper.URL + "image?name=" + safeinfo.safeDesUrl);
            } else {
                //隐藏没数据的view
                mll_Des[i].setVisibility(View.GONE);
                mSafeIcon[i].setVisibility(View.GONE);
            }
        }

        ll_des_root.measure(0, 0);
        mDesHeight = ll_des_root.getMeasuredHeight();
        LogUtils.d("安全信息描述布局的高度=======" + mDesHeight);
        mParams = (LinearLayout.LayoutParams) ll_des_root.getLayoutParams();
        mParams.height = 0;
        ll_des_root.setLayoutParams(mParams);

    }
}
