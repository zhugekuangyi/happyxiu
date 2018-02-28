package com.mingsheng.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface PhoneMapper {


    String getPhone(@Param(value = "id") String s);
}
