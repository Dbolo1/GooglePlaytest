package com.bolo1.googleplay.ui.http.protocol;

import android.view.View;

import com.bolo1.googleplay.domain.AppInfo;
import com.bolo1.googleplay.ui.holder.BaseHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/27.
 */

public class AppProtocol extends BaseProtocol<ArrayList<AppInfo>> {

    private ArrayList<AppInfo> arrayList;

    @Override
    public String getParam() {
        return "";
    }

    @Override
    public String getKey() {
        return "app";
    }

    @Override
    public ArrayList<AppInfo> parseData(String result) {
        //进行json解析
        try {
            JSONArray ja = new JSONArray(result);
            //保存jsonobject解析数据
            arrayList = new ArrayList<AppInfo>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo1 = ja.getJSONObject(i);
                AppInfo info = new AppInfo();
                info.des = jo1.getString("des");
                info.downloadUrl = jo1.getString("downloadUrl");
                info.iconUrl = jo1.getString("iconUrl");
                info.id = jo1.getString("id");
                info.name = jo1.getString("name");
                info.packageName = jo1.getString("packageName");
                info.size = jo1.getLong("size");
                info.stars = jo1.getDouble("stars");
                arrayList.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
