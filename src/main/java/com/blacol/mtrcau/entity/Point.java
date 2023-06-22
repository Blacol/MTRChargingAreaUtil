package com.blacol.mtrcau.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private Double x;
    private Double y;
    public static Point valueOf(String pointJSONStr){
        return JSONObject.parseObject(pointJSONStr,Point.class);
    }
    public static Point valueOf(int x,int y){
        return new Point((double) x, (double) y);
    }
}
