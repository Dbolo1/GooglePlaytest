package com.bolo1.googleplay.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.ui.http.protocol.RecommendProtocol;
import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.ui.view.fly.ShakeListener;
import com.bolo1.googleplay.ui.view.fly.StellarMap;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.UIUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public  class RecommendFragment extends BaseFragment{

    private ArrayList<String> data;
    @Override
    public View onCreateSuccessView() {
        final StellarMap stellarMap = new StellarMap(UIUtils.getContext());

        stellarMap.setAdapter(new RecommendAdapter());
        LogUtils.d("此步骤为2创建布局");
       // 设置格子
       stellarMap.setRegularity(6,9);
        //设置格子的内边距
        int padding1 = UIUtils.dip2px(10);
        stellarMap.setInnerPadding(padding1,padding1,padding1,padding1);
       //设置初始界面
        stellarMap.setGroup(0,true);
        ShakeListener  shakeListener=new ShakeListener(UIUtils.getContext());
        shakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                stellarMap.zoomIn();
            }
        });
        return stellarMap;
    }

    @Override
    public LoadingPage.ResultState initData() {
        LogUtils.d("此步骤为1初始化数据");
        RecommendProtocol protocol = new  RecommendProtocol();
        data = protocol.getData(0);
        return check(data);
    }

    class RecommendAdapter implements StellarMap.Adapter {
        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getCount(int group) {
            int count = data.size() / getGroupCount();
            if(group==getGroupCount()-1){
                //当最后一页还有多余数据,则加上一起显示
                count+=data.size()%getGroupCount();
            }
            LogUtils.d("一次滑动的数据大小"+count);
            return count;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            position+=(group)*getCount(group-1);
            TextView  view= new TextView(UIUtils.getContext());
            final String keyWord = data.get(position);
            LogUtils.d("滑动展示当前数据"+keyWord);
            view.setText(keyWord);

            //设置字体随机大小
              Random  random=new Random();
            //16-25
            int size=16+random.nextInt(10);
            view.setTextSize(size);
            //设置字体随机颜色
            int r=40+random.nextInt(200);
            int g=40+random.nextInt(200);
            int b=40+random.nextInt(200);
            view.setTextColor(Color.rgb(r,g,b));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(),keyWord,Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            LogUtils.d("判断上滑还是下滑"+isZoomIn);
            if(isZoomIn){
                //向下滑加载上一页
                if (group>0){
                    //当所在位置大于0时
                    group--;
                }else{
                    group=getGroupCount()-1;
                }
            }else {
                //下上滑加载下一页
                if(group<getGroupCount()-1){
                    group++;
                }else {
                    group=0;
                }
            }
            return group;
        }
    }



}
