package com.bolo1.googleplay.ui.http.protocol;

import com.bolo1.googleplay.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/30.
 */

public class HomeDetailProtocol extends BaseProtocol<AppInfo> {
    private final String packageName;
    private ArrayList<AppInfo> mAppList;

    public HomeDetailProtocol(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String getParam() {
        return "&packageName=" + packageName;
    }

    @Override
    public String getKey() {
        return "detail";
    }

    @Override

    public AppInfo parseData(String result) {
        try {
            JSONObject jo = new JSONObject(result);
            mAppList = new ArrayList<AppInfo>();
            AppInfo info = new AppInfo();
            info.des = jo.getString("des");
            info.downloadUrl = jo.getString("downloadUrl");
            info.iconUrl = jo.getString("iconUrl");
            info.id = jo.getString("id");
            info.name = jo.getString("name");
            info.packageName = jo.getString("packageName");
            info.size = jo.getLong("size");
            info.stars = jo.getDouble("stars");
            info.author = jo.getString("author");
            info.date = jo.getString("date");
            info.downloadNum = jo.getString("downloadNum");
            info.version = jo.getString("version");
            JSONArray ja = jo.getJSONArray("safe");
            //解析safe
            ArrayList<AppInfo.SafeInfo> safeInfolist = new ArrayList<AppInfo.SafeInfo>();
            for (int i = 0; i < ja.length(); i++) {
                AppInfo.SafeInfo safeinfo = new AppInfo.SafeInfo();
                JSONObject jo1 = (JSONObject) ja.get(i);
                safeinfo.safeDes = jo1.getString("safeDes");
                safeinfo.safeDesUrl = jo1.getString("safeDesUrl");
                safeinfo.safeUrl = jo1.getString("safeUrl");
                safeInfolist.add(safeinfo);
            }
            info.safe = safeInfolist;
            JSONArray ja1 = jo.getJSONArray("screen");
            ArrayList<String> screenlist = new ArrayList<String>();
            for (int i = 0; i < ja1.length(); i++) {
                screenlist.add(ja1.getString(i));
            }
            info.screen = screenlist;

            return info;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
