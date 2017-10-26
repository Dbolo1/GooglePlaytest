package com.bolo1.googleplay.ui.holder;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.ui.adapter.MyBaseAdapter;
import com.bolo1.googleplay.utils.UIUtils;

/**
 * Created by 菠萝 on 2017/10/18.
 */

public class MoreHolder extends BaseHolder<Integer> {
    public  static final int STATE_MORE_MORE = 1; //加载更多数据
    public static final int STATE_MORE_ERROR = 2; //加载数据错误
    public static final int STATE_MORE_NONE = 3; //没有更多数据
    private LinearLayout ll_load_more;
    private TextView tv_load_error;
    private static final String tag="MoreHolder";

    public MoreHolder(boolean hasMore) {
        //传给父类data数据
        setData(hasMore ? STATE_MORE_MORE : STATE_MORE_NONE);
    }
    @Override
    public View initView() {
        View view = UIUtils.getinflate(R.layout.list_item_more);
        ll_load_more = (LinearLayout) view.findViewById(R.id.ll_load_more);
        tv_load_error = (TextView) view.findViewById(R.id.tv_load_error);
        return view;
    }

    @Override
    public void refreshView(Integer data) {
        switch (data) {
            case STATE_MORE_MORE:
                Log.d(tag,"加载出数据啦啦啦啦啦啦啦！~~~~");
                ll_load_more.setVisibility(View.VISIBLE);
                tv_load_error.setVisibility(View.GONE);
                break;
            case STATE_MORE_NONE:
                Log.d(tag,"加载数据隐藏啦啦啦啦啦啦啦！~~~~");
                ll_load_more.setVisibility(View.GONE);
                tv_load_error.setVisibility(View.GONE);
                break;
            case STATE_MORE_ERROR:
                Log.d(tag,"加载数据出错啦啦啦啦啦啦啦！~~~~");
                ll_load_more.setVisibility(View.GONE);
                tv_load_error.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }



    }
}
