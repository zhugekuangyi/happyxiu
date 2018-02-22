package com.mingsheng.mapper;

import com.mingsheng.model.MobileSale;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MobileSaleMapper {

        List<MobileSale> getList(@Param(value = "page") Integer page,@Param(value = "limit") Integer limit);


    MobileSale getInfo(@Param(value = "mobileType") String pid,
                       @Param(value = "mobileName") String id,
                       @Param(value = "mobileMemory") String mobileMemory,
                       @Param(value = "mobileColour") String mobileColour);

    MobileSale getInfoById(@Param(value = "id") String mobileId);

    List<MobileSale> getListNoPage();

    void insert(MobileSale mobileSale);

    void del(@Param(value = "id") String id);
}
