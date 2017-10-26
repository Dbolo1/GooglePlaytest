package com.bolo1.googleplay.ui.http.protocol;

import android.text.TextUtils;
import android.util.Log;

import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.http.HttpHelper;
import com.bolo1.googleplay.utils.IOUtils;
import com.bolo1.googleplay.utils.LogUtils;
import com.bolo1.googleplay.utils.StringUtils;
import com.bolo1.googleplay.utils.UIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/18.
 */

public abstract class BaseProtocol<T> {

    private static final String tag = "BaseProtocol";

    public T getData(int index) {
        //从服务器获得数据
        //优先取得缓存
        String result = getCache(index);
        LogUtils.d("取得缓存数据"+result);
        if (StringUtils.isEmpty(result)) {
            //如果缓存为空，则请求网络
            LogUtils.d("缓存为空，请求网络");
            result = getDataFromService(index);
        }
        //如果result不如空
        if (result != null) {

            T data = parseData(result);
            LogUtils.d("缓存不为空，直接解析所得结果" + data);
            return data;
//            return parseData(result);
        }
        return null;
    }

    public String getDataFromService(int index) {

        HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.URL + getKey() + "?index=" + index + getParam());
        if (httpResult != null) {
            String result = httpResult.getString();
            //在此设置缓存
            LogUtils.d("设置缓存" + result);
            if (!StringUtils.isEmpty(result)) {
                setCache(index, result);
                LogUtils.d("设置的缓存数据" + result);
            }
            return result;
        }
        return null;
    }

    //设置缓存 以url为key 以json为value
    public void setCache(int index, String json) {
        File cacheDir = UIUtils.getContext().getCacheDir();
        File cacheFile = new File(cacheDir, getKey() + "?index=" + index + getParam());
        FileWriter writer = null;
        try {
            writer = new FileWriter(cacheFile);
            //为缓存加上有效时间
            long deadline = System.currentTimeMillis() + 30 * 60 * 1000;
            writer.write(deadline + "\n");
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(writer);
        }
    }

    //获得缓存
    public String getCache(int index) {
        File cacheDir = UIUtils.getContext().getCacheDir();
        File cacheFile = new File(cacheDir, getKey() + "?index=" + index + getParam());
        if (cacheFile.exists()) {
            //如果缓存存在
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(cacheFile));
                //判断缓存是否有效
                String deadline = reader.readLine();
                long deadtime = Long.parseLong(deadline);
                if (System.currentTimeMillis() < deadtime) {
                    //缓存有效
                    StringBuffer sb = new StringBuffer();
                    //非空判断
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    return sb.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(reader);
            }
        }
        return null;
    }


    //从子类获得参数
    public abstract String getParam();

    //从子类获得关键字
    public abstract String getKey();

    //解析数据交给子类
    public abstract T parseData(String result);


}
