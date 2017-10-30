package com.bolo1.googleplay.ui.http.protocol;

import android.util.Log;

import com.bolo1.googleplay.ui.http.protocol.BaseProtocol;
import com.bolo1.googleplay.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/27.
 */

public class RecommendProtocol extends BaseProtocol<ArrayList<String>> {

    private ArrayList<String> list;

    @Override
    public String getParam() {
        return "";
    }

    @Override
    public String getKey() {
        return "recommend";
    }

    @Override
    public ArrayList<String> parseData(String result) {
        try {
            JSONArray ja = new JSONArray(result);
            list = new ArrayList<String>();
            for (int i = 0; i < ja.length(); i++) {
                String keyword = ja.getString(i);
                list.add(keyword);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.d("Recommend解析返回的数据"+list);
        return list;
    }
}
