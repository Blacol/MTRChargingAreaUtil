package com.blacol.mtrcau.controller;

import com.blacol.messageUtil.core.MessageUtil;
import com.blacol.messageUtil.entity.Message;
import com.blacol.mtrcau.entity.City;
import com.blacol.mtrcau.mapper.impl.CityMapperServiceImpl;
import com.blacol.mtrcau.util.IdUtil;
import gitee.blacol.myIdUtil.entity.ComplexId;
import gitee.blacol.myIdUtil.exception.MissingNecessaryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityMapperServiceImpl cityMapperService;

    private ComplexId id;
    public CityController(){
        try {
            id=IdUtil.getComplexId("C","%d","cityId");

        } catch (IOException | MissingNecessaryParameters e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/create")
    public Message<Boolean> createCity(@RequestBody City city){
        if (city!=null){
            try {
                city.setId(id.generic());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            boolean result = cityMapperService.save(city);

            return result?MessageUtil.sendOkMessage(result):MessageUtil.sendFailMessage(500,"插入数据失败",result);
        }
        return MessageUtil.sendFailMessage(400,"网络传输错误",false);
    }
    @RequestMapping("/getAll")
    public Message<List<City>> getAll(){
        List<City> list = cityMapperService.list();
        return MessageUtil.sendOkMessage(list);
    }
    @RequestMapping("/delete/{id}")
    public Message<Boolean> delete(@PathVariable("id")String id){
        boolean b = cityMapperService.removeById(id);
        return b?MessageUtil.sendOkMessage(b):MessageUtil.sendFailMessage(500,"删除失败，数据库错误");
    }
    @RequestMapping("/update/{id}")
    public Message<Boolean> update(@PathVariable("id")String id,@RequestBody City city){
        city.setId(id);
        boolean b = cityMapperService.updateById(city);
        return b?MessageUtil.sendOkMessage(b):MessageUtil.sendFailMessage(500,"删除失败，数据库错误");
    }
}
