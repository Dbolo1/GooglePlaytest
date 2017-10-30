package com.bolo1.googleplay.domain;

import java.util.ArrayList;

/**
 * Created by 菠萝 on 2017/10/19.
 */

public class AppInfo {
//      "des": "产品介绍：有缘是时下最受大众单身男女亲睐的婚恋交友软件。有缘网专注于通过轻松、",
//              "downloadUrl": "app/com.youyuan.yyhl/com.youyuan.yyhl.apk",
//              "iconUrl": "app/com.youyuan.yyhl/icon.jpg",
//              "id": 1525490,
//              "name": "有缘网",
//              "packageName": "com.youyuan.yyhl",
//              "size": 3876203,
//              "stars": 4


    public String des;
    public String downloadUrl;
    public String iconUrl;
    public String id;
    public String name;
    public String packageName;
    public long size;
    public double stars;


    //解析详情页数据
    public String author;
    public String date;
    public String downloadNum;
    public String version;
    public ArrayList<SafeInfo> safe;
    public ArrayList<String> screen;


    public static class SafeInfo {
        public String safeDes;
        public String safeDesUrl;
        public String safeUrl;
    }



}
