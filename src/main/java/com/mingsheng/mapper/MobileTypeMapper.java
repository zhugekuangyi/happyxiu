package com.mingsheng.mapper;

import com.mingsheng.model.MobileType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MobileTypeMapper {

    List<MobileType> getList(@Param(value = "status") Integer status);

    List<MobileType>getListByPid(@Param(value = "id") String id);

    MobileType getInfoById(@Param(value = "id") String id);

    List<MobileType> getMobileList();

    void del(@Param(value = "id") String id);
}
