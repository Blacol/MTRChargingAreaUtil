package com.blacol.mtrcau.util;

import com.alibaba.fastjson.JSONObject;
import com.blacol.mtrcau.entity.Point;
import com.blacol.mtrcau.entity.Points;
import com.blacol.mtrcau.vo.CauculateResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SquareAreaCauculater {
    private static final String[] position={"LT","LB","RT","RB"};
    private static final RoundingMode roundingMode=RoundingMode.UP;

    /**
     * 判断某个点是否在正方形区域内
     * @param point 要判断的点
     * @param range 正方形的范围（4个顶点的坐标）
     * @return 如果该点在范围内则返回真，否则为假
     */
    public static Boolean inRange(Point point,Points range){
        String ltJStr = String.valueOf(range.get("LT"));
        Point lt = JSONObject.parseObject(ltJStr,Point.class);
        Double line1B=lt.getX();
        Boolean line1BCondition=point.getX()<line1B;
        Double line2B=lt.getY();
        Boolean line2BCondition=point.getY()>line2B;
        String rbJStr = String.valueOf(range.get("RB"));
        Point rb = JSONObject.parseObject(rbJStr,Point.class);
        Double line3B=rb.getX();
        Boolean line3BCondition=point.getX()>line3B;
        Double line4B=rb.getY();
        Boolean line4BCondition=point.getY()<line4B;
        return line1BCondition&&line2BCondition&&line3BCondition&&line4BCondition;
    }
    /**
     * 判断某个点在哪个正方形范围内。正方形范围由{@code LDelta}等差计算而来。
     * @param point 要判断的点
     * @param center 中心点
     * @param rangeList 多个正方形区域的顶点坐标
     * @param LDelta 正方形范围的等差增量。
     * @return 返回在哪个编号的正方形区域内（编号从1开始，最大为10），如果不在10环以内则返回-1。
     */
    public static Integer inWhichRange(Point point,Point center,List<Points> rangeList,Integer LDelta){
        Double distance = CaulculaterBasic.getDistance(point, center);
        BigDecimal distanceB=new BigDecimal(String.valueOf(distance));
        BigDecimal LDeltaB=new BigDecimal(LDelta);
        BigDecimal number = distanceB.divide(LDeltaB,roundingMode)
                .setScale(0,roundingMode);
        int max=rangeList.size();
        if (number.compareTo(new BigDecimal(1))<0){
            return 1;
        }else{
            Integer index=number.intValue();
            if (index<=10){
                for (int i=0;i<max-1-index;i++){
                    if(inRange(point,rangeList.get(index-1+i))){
                        return index;
                    }
                }
                return -1;
            }else{
                return -1;
            }
        }
    }


    /**
     * 获取收费区编号（按照正方形区域划分）
     * @param point 坐标点
     * @param center 正方形区域中心点
     * @param rangeList 所有正方形区域的顶点坐标
     * @param LDelta 正方形区域边长增量
     * @param CADelta 收费区编号增量
     * @return 收费区编号，如果某个点超出了10环的范围则按照超出中心点多少距离计算收费区编号
     */
    public static Integer getChargeAreaNumber(Point point,Point center,List<Points> rangeList,Integer LDelta,Integer CADelta){
        Integer integer = inWhichRange(point, center, rangeList, LDelta);
        if (integer!=-1){
            return (integer-1)*CADelta;
        }else{
            return CaulculaterBasic.outOf10Ring(point, center, LDelta,rangeList.size());
        }
    }
}
