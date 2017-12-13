package com.mingsheng.mapper;

import com.mingsheng.model.StoreAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public interface StoreAddressMapper {


    void insert(@Param(value = "id") String id,
                @Param(value = "address") String address,
                @Param(value = "remark") String remark,
                @Param(value = "img") String img,
                @Param(value = "title") String title,
                @Param(value = "ctime") Timestamp ctime);

    List<StoreAddress> getStoreAddress();
}
