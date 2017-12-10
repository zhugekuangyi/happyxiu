package com.mingsheng.service;

import com.mingsheng.mapper.BrandMapper;
import com.mingsheng.model.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private BrandMapper mapper;

    public List<Brand> getList(){

        return mapper.list();
    }
}
