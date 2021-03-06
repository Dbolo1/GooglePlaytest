package com.bolo1.googleplay.utils;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by 菠萝 on 2017/10/27.
 */

public class BitmapHelper {

    private static BitmapUtils mBitmapUtils = null;

    public static BitmapUtils getBitmapUtils() {
        if (mBitmapUtils == null) {
            synchronized (BitmapHelper.class) {
                if (mBitmapUtils == null) {
                    mBitmapUtils = new BitmapUtils(UIUtils.getContext());
                }
            }
        }
        return mBitmapUtils;
    }
}
