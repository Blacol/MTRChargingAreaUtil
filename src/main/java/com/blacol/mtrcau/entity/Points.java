package com.blacol.mtrcau.entity;

import com.alibaba.fastjson.JSONArray;

import java.util.LinkedHashMap;
import java.util.List;

public class Points extends LinkedHashMap<String,Point> {
    public static List<Points> toList(String listJSONStr){
        return JSONArray.parseArray(listJSONStr, Points.class);
    }
    public static String toJSONString(List<Points> list){
        return JSONArray.toJSONString(list);
    }
}
