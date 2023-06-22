package com.blacol.mtrcau.util;

import com.blacol.mtrcau.entity.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CaulculaterBasic {
    protected static final RoundingMode roundingMode=RoundingMode.UP;
    /**
     * 计算两点间的距离
     * @param a 点1
     * @param b 点2
     * @return 距离
     */
    public static Double getDistance(Point a, Point b){
        Double x2x1=Math.pow(a.getX()-b.getX(),2);
        Double y2y1=Math.pow(a.getY()-b.getY(),2);
        return Math.sqrt(x2x1+y2y1);
    }

    /**
     * 超出10环后的收费区计算方法
     * @param point 要判断的点
     * @param center 中心点
     * @param LDelta 半径/正方形边长增量
     * @param max 最大范围
     * @return 收费区编号
     */
    protected static int outOf10Ring(Point point, Point center, Integer LDelta,int max) {
        Double distance = getDistance(point, center);
        Double v = distance - 10 * LDelta;
        if (v<0){
            v=0d;
        }
        return Integer.parseInt(String.valueOf(new BigDecimal(v / 100+max).setScale(0,roundingMode)));
    }
}
