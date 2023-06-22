package com.blacol.mtrcau.util;

import com.alibaba.fastjson.JSONObject;
import com.blacol.mtrcau.entity.Point;
import com.blacol.mtrcau.entity.Points;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CircleAreaCaulculater extends CaulculaterBasic{
    /**
     * 判断某个点是否在圆形区域范围内
     * @param point 要判断的点
     * @param center 中心点
     * @param r 圆的半径
     * @return 如果该点在范围内则返回真，否则为假
     */
    public static Boolean inRange(Point point,Point center,Double r){
        return CaulculaterBasic.getDistance(point,center)<=r;
    }

    /**
     * 判断某个点在哪个圆形范围内。圆形范围由{@code distanceData}等差计算而来。
     * @param point 要判断的点
     * @param center 中心点
     * @param distanceDelta 圆形范围的等差增量
     * @return 返回在哪个编号的圆形区域内（编号从1开始，最大为10），如果不在10环以内则返回-1
     */

    public static Integer inWhichRange(Point point,Point center,Integer distanceDelta,Integer ringLimit){
        Double distance = CaulculaterBasic.getDistance(point, center);
        if (distance<distanceDelta){
            return 1;
        }else{
            BigDecimal distanceB=new BigDecimal(String.valueOf(distance));
            BigDecimal deltaB=new BigDecimal(distanceDelta);
            BigDecimal number = distanceB.divide(deltaB, roundingMode)
                    .setScale(0,roundingMode);
            if (number.intValue()>ringLimit||number.intValue()>10){
                return -1;
            }else if(number.intValue()<=ringLimit&&number.intValue()<=10){
                return number.intValue();
            }else{
                return CaulculaterBasic.outOf10Ring(point,center,distanceDelta,ringLimit);
            }
        }
    }

    /**
     * 获取收费区编号（按照圆形区域划分）
     * @param point 坐标点
     * @param center 正方形区域中心点
     * @param r 半径
     * @param CADelta 收费区编号增量
     * @return 收费区编号，如果某个点超出了10环的范围则按照超出中心点多少距离计算收费区编号
     */
    public static Integer getChargeAreaNumber(Point point,Point center,Integer r,Integer CADelta,int max){
        Integer integer = inWhichRange(point, center,r,max);
        if (integer!=-1){
            return (integer-1)*CADelta;
        }else{
            return CaulculaterBasic.outOf10Ring(point, center, r,max);
        }
    }
}
