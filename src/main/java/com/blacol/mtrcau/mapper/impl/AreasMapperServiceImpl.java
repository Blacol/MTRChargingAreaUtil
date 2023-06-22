package com.blacol.mtrcau.mapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blacol.mtrcau.entity.Areas;
import com.blacol.mtrcau.mapper.AreasMapper;
import com.blacol.mtrcau.mapper.service.AreasMapperService;
import org.springframework.stereotype.Service;

@Service
public class AreasMapperServiceImpl extends ServiceImpl<AreasMapper, Areas> implements AreasMapperService {
}
