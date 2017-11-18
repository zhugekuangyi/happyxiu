package com.mingsheng.mapper;

import com.mingsheng.model.MobileType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MobileTypeMapper {

    List<MobileType> getList();

    List<MobileType>getListByPid(@Param(value = "id") String id);
}
