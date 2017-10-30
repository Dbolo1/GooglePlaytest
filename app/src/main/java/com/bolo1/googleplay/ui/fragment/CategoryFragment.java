package com.bolo1.googleplay.ui.fragment;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bolo1.googleplay.domain.CategoryInfo;
import com.bolo1.googleplay.ui.adapter.MyBaseAdapter;
import com.bolo1.googleplay.ui.holder.BaseHolder;
import com.bolo1.googleplay.ui.holder.CategoryHolder;
import com.bolo1.googleplay.ui.holder.TitleHolder;
import com.bolo1.googleplay.ui.http.protocol.CategoryProtocol;
import com.bolo1.googleplay.ui.view.LoadingPage;
import com.bolo1.googleplay.ui.view.MyListVIew;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public class CategoryFragment extends BaseFragment {

    private ArrayList<CategoryInfo> data;

    @Override
    public View onCreateSuccessView() {
        MyListVIew listVIew = new MyListVIew(UIUtils.getContext());
        listVIew.setAdapter(new CategoryAdapter(data));
        return listVIew;
    }

    @Override
    public LoadingPage.ResultState initData() {
        CategoryProtocol categoryProtocol = new CategoryProtocol();
        data = categoryProtocol.getData(0);
        return check(data);
    }


    private class CategoryAdapter extends MyBaseAdapter {

        public CategoryAdapter(ArrayList data) {
            super(data);
        }
        //没有更多分页

        @Override
        public boolean hasMore() {
            return false;
        }

        @Override
        public int getInnerType(int position) {
            CategoryInfo info = data.get(position);
            if (info.isTitle) {
                //返回标题
                return super.getInnerType(position) + 1;
            } else {
                //返回普通内容
                return super.getInnerType(position);
            }

        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;
    }

        @Override
        public BaseHolder getHolder(int position) {
            CategoryInfo info=data.get(position);
            if(info.isTitle){
                //标题页
                return new TitleHolder();
            }else{
                //分类信息
                return new CategoryHolder();
            }
        }

        @Override
        public ArrayList onLoadMore() {
            return null;
        }
    }
}
