package com.bolo1.googleplay.ui.http.protocol;

import com.bolo1.googleplay.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/28.
 */

public class HotProtocol extends BaseProtocol<ArrayList<String>> {
    private ArrayList<String> list;
    @Override
    public String getParam() {
        return "";
    }

    @Override
    public String getKey() {
        return "hot";
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

        return list;
    }
    }

