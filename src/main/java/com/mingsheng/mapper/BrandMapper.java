package com.mingsheng.mapper;

import com.mingsheng.model.Brand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BrandMapper {

    List<Brand> list(@Param(value = "type") Integer type);
}
