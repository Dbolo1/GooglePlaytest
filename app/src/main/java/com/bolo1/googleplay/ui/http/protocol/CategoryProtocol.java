package com.bolo1.googleplay.ui.http.protocol;

import com.bolo1.googleplay.domain.CategoryInfo;
import com.bolo1.googleplay.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/28.
 */

public class CategoryProtocol extends BaseProtocol<ArrayList<CategoryInfo>> {
    @Override
    public String getParam() {
        return "";
    }

    @Override
    public String getKey() {
        return "category";
    }

    @Override
    public ArrayList<CategoryInfo> parseData(String result) {
        try {
            JSONArray ja = new JSONArray(result);
            ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);

                //解析title
                if (jo.has("title")) {
                    CategoryInfo titleinfo = new CategoryInfo();
                    titleinfo.title = jo.getString("title");
                    titleinfo.isTitle = true;
                    list.add(titleinfo);
                }
                //解析分类
                if (jo.has("infos")) {
                    JSONArray ja1 = jo.getJSONArray("infos");
                    for (int j = 0; j < ja1.length(); j++) {
                        CategoryInfo info = new CategoryInfo();
                        JSONObject jo1 = ja1.getJSONObject(j);
                        info.name1 = jo1.getString("name1");
                        info.name2 = jo1.getString("name2");
                        info.name3 = jo1.getString("name3");
                        info.url1 = jo1.getString("url1");
                        info.url2 = jo1.getString("url2");
                        info.url3 = jo1.getString("url3");
                        info.isTitle = false;

                        list.add(info);
                    }
                }
                LogUtils.d("分页返回的消息解析+==============="+list);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
