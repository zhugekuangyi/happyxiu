package com.mingsheng.mapper;

import com.mingsheng.model.MobileRecovery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MobileRecoveryMapper {

        List<MobileRecovery> getList();

    MobileRecovery getInfo(@Param(value = "mobileType") String pid, @Param(value = "mobileName") String id);

    MobileRecovery getInfoById(@Param(value = "id") String mobileId);
}
