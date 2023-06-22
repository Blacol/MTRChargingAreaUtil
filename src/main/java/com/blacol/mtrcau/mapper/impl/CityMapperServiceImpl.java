package com.blacol.mtrcau.mapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blacol.mtrcau.entity.City;
import com.blacol.mtrcau.mapper.CityMapper;
import com.blacol.mtrcau.mapper.service.CityMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityMapperServiceImpl extends ServiceImpl<CityMapper,City> implements CityMapperService {
    @Autowired
    private CityMapper cityMapper;


}
