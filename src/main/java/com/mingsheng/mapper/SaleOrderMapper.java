package com.mingsheng.mapper;

import com.mingsheng.model.SaleOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SaleOrderMapper {

    void addOrder(SaleOrder order);

    List<SaleOrder> getListByUserId(@Param(value = "userId") String userId);
}
