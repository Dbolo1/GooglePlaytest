package com.bolo1.googleplay.ui.http.protocol;

import com.bolo1.googleplay.domain.SubjectInfo;
import com.bolo1.googleplay.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/27.
 */

public class SubjectProtocol extends BaseProtocol<ArrayList<SubjectInfo>> {


    private ArrayList<SubjectInfo> list;

    @Override
    public String getParam() {
        return "";
    }

    @Override
    public String getKey() {
        return "subject";
    }

    @Override
    public ArrayList<SubjectInfo> parseData(String result) {
        try {
            JSONArray ja = new JSONArray(result);
            list = new ArrayList<SubjectInfo>();
            for (int i=0;i<ja.length();i++){
                JSONObject  jo=ja.getJSONObject(i);
                SubjectInfo info=new SubjectInfo();
                info.url=jo.getString("url");
                info.des=jo.getString("des");
                list.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.d("主题解析的数据"+list);
        return  list;
    }
}
