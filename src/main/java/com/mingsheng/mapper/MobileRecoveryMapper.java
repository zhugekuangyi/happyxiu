package com.mingsheng.mapper;

import com.mingsheng.model.MobileRecovery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MobileRecoveryMapper {

        List<MobileRecovery> getList(@Param(value = "page") Integer page,@Param(value = "limit") Integer limit);

    MobileRecovery getInfo(@Param(value = "mobileType") String pid, @Param(value = "mobileName") String id);

    MobileRecovery getInfoById(@Param(value = "id") String mobileId);

    List<MobileRecovery> getListNoPage();

    void del(@Param(value = "id") String id);
}
