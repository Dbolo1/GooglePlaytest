package com.bolo1.googleplay.ui.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bolo1.googleplay.R;
import com.bolo1.googleplay.utils.UIUtils;

/**
 * 根据状态来显示界面
 * 未加载--加载中--加载失败--加载数据为空--加载成功
 * <p>
 * Created by 菠萝 on 2017/10/16.
 */

public abstract class LoadingPage extends FrameLayout {
    public static int STATE_LOAD_UNDO = 1;//未加载
    public static int STATE_LOAD_LOADING = 2;//加载中
    public static int STATE_LOAD_ERROR = 3;//加载失败
    public static int STATE_LOAD_EMPTY = 4;//数据为空
    public static int STATE_LOAD_SUCCESS = 5;//加载成功
    public static int CURRENT_STATE = STATE_LOAD_UNDO;//当前状态
    private View page_loading;
    private View page_error;
    private View page_empty;
    private View mSuccessPage;
    private static final String tag = "LoadingPage";


    //改变了调用方法
    public LoadingPage(@NonNull Context context) {
        this(context,null);

    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //加载中图片
        if (page_loading == null) {
            page_loading = UIUtils.getinflate(R.layout.page_loading);
            addView(page_loading);
        }
        //加载失败图片
        if (page_error == null) {
            page_error = UIUtils.getinflate(R.layout.page_error);
            Button bt_retry_load = (Button) page_error.findViewById(R.id.bt_retry_load);
            bt_retry_load.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLoadData();
                }
            });
            addView(page_error);
        }
        //数据为空图片
        if (page_empty == null) {
            page_empty = UIUtils.getinflate(R.layout.page_empty);
            addView(page_empty);
        }
        showRightPage();
    }

    //根据当前状态显示不同图片
    private void showRightPage() {
        page_loading.setVisibility((CURRENT_STATE == STATE_LOAD_UNDO || CURRENT_STATE == STATE_LOAD_LOADING )? View.VISIBLE : View.GONE);
        page_error.setVisibility(CURRENT_STATE == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);
        page_empty.setVisibility(CURRENT_STATE == STATE_LOAD_EMPTY ? View.VISIBLE : View.GONE);
        if (mSuccessPage == null && CURRENT_STATE == STATE_LOAD_SUCCESS) {
            mSuccessPage = onCreateSuccessView();
            if (mSuccessPage != null) {
                addView(mSuccessPage);
            }
        }
        if (mSuccessPage != null) {
            mSuccessPage.setVisibility(CURRENT_STATE == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

    //加载数据
    public void onLoadData() {
        if (CURRENT_STATE != STATE_LOAD_LOADING) {
            CURRENT_STATE = STATE_LOAD_LOADING;

            new Thread() {
                @Override
                public void run() {
                    final ResultState resultState = onLoad();
                 //   运行主线程
                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (resultState != null) {
                                //获得当前状态并赋值
                                CURRENT_STATE = resultState.getState();
                                Log.d(tag,"当前的状态===="+CURRENT_STATE);
                                showRightPage();
                                CURRENT_STATE=STATE_LOAD_UNDO;
                            }
                        }
                    });

                }
            }.start();
        }
    }

    //由子类去创建请求成功时候的view
    public abstract View onCreateSuccessView();

    //由子类去获得数据
    public abstract ResultState onLoad();

    public enum ResultState {
        STATE_SUCCESS(STATE_LOAD_SUCCESS),
        STATE_EMPTY(STATE_LOAD_EMPTY),
        STATE_ERROR(STATE_LOAD_ERROR);
        private int state;

        ResultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;

        }
    }

}
