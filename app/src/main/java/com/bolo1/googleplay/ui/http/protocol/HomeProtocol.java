package com.bolo1.googleplay.ui.http.protocol;

import android.util.Log;

import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/19.
 */

public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {

    private ArrayList<AppInfo> mAppList;
    private ArrayList<String> mPicList;


    @Override
    public String getParam() {
        return "";
    }

    @Override
    public String getKey() {
        return "home";
    }

    @Override
    public ArrayList<AppInfo> parseData(String result) {
        //用jsonobject解析
        try {
            JSONObject jo = new JSONObject(result);
            JSONArray ja = jo.getJSONArray("list");
            mAppList = new ArrayList<AppInfo>();
            for (int i = 0; i < ja.length(); i++) {
                AppInfo  info=   new AppInfo();
                 JSONObject  jo1 = (JSONObject) ja.get(i);
                info.des = jo1.getString("des");
                info.downloadUrl = jo1.getString("downloadUrl");
                info.iconUrl = jo1.getString("iconUrl");
                info.id = jo1.getString("id");
                info.name = jo1.getString("name");
                info.packageName = jo1.getString("packageName");
                info.size = jo1.getLong("size");
                info.stars = jo1.getDouble("stars");
                mAppList.add(info);
            }
            //解析头条新闻
            //创建一个用于保存picture的数组
            mPicList = new ArrayList<String>();
            JSONArray ja1 = jo.getJSONArray("picture");
            for (int j = 0; j < ja1.length(); j++) {
                mPicList.add(ja1.getString(j));
                System.out.println(">>>>>>>>"+ja1.getString(j));

            }

            return mAppList;
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<String> getPicList(){
        if(mPicList!=null){
            return mPicList;
        }else {
            return null;
        }
    }


}
