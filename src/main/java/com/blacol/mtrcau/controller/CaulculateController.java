package com.blacol.mtrcau.controller;

import com.alibaba.fastjson.JSONObject;
import com.blacol.messageUtil.core.MessageUtil;
import com.blacol.messageUtil.entity.Message;
import com.blacol.mtrcau.entity.City;
import com.blacol.mtrcau.entity.Point;
import com.blacol.mtrcau.mapper.impl.AreasMapperServiceImpl;
import com.blacol.mtrcau.mapper.impl.CityMapperServiceImpl;
import com.blacol.mtrcau.util.CircleAreaCaulculater;
import com.blacol.mtrcau.vo.CauculateResult;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CaulculateController {
    @Autowired
    private CityMapperServiceImpl cityMapperService;
    @Autowired
    private AreasMapperServiceImpl areasMapperService;

    @RequestMapping("/caulculate")
    public Message<CauculateResult> cauculate(@RequestBody JSONObject data){
        String id = data.getString("id");
        Point point = data.getObject("point", Point.class);
        City targetCity = cityMapperService.getById(id);
        if (targetCity!=null){
            CauculateResult cauculateResult = getCauculateResult(point, targetCity);
            return MessageUtil.sendOkMessage(cauculateResult);
        }else{
            return MessageUtil.sendFailMessage(500,"没有这个城市",null);
        }
    }

    @NotNull
    private static CauculateResult getCauculateResult(Point point, City targetCity) {
        Integer rangeNumber = CircleAreaCaulculater.inWhichRange(point, Point.valueOf(targetCity.getX(), targetCity.getY()), targetCity.getDistanceDelta(),targetCity.getRingLimit());
        Integer chargeAreaNumber = CircleAreaCaulculater.getChargeAreaNumber(point, Point.valueOf(targetCity.getX(), targetCity.getY()), targetCity.getDistanceDelta()
                , targetCity.getChargingAreaDelta(),targetCity.getRingLimit());
        Double distance= CircleAreaCaulculater.getDistance(point,Point.valueOf(targetCity.getX(), targetCity.getY()));
        return new CauculateResult(chargeAreaNumber,rangeNumber,distance);
    }
}
