package com.mingsheng.service;

import com.mingsheng.mapper.BrandMapper;
import com.mingsheng.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper mapper;

    public List<Brand> getList(Integer type){

        return mapper.list(type);
    }
}
