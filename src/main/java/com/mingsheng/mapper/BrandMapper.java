package com.mingsheng.mapper;

import com.mingsheng.model.Brand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BrandMapper {

    List<Brand> list();
}
