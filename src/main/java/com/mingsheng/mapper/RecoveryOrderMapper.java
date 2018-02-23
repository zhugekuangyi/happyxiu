package com.mingsheng.mapper;

import com.mingsheng.model.RecoveryOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecoveryOrderMapper {

    void addOrder(RecoveryOrder order);

    List<RecoveryOrder> getListByUserId(@Param(value = "userId") String id);

    RecoveryOrder getOrderById(@Param(value = "id") String orderId);

    List<RecoveryOrder> getList();
}
