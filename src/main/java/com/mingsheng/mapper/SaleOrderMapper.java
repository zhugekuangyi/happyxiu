package com.mingsheng.mapper;

import com.mingsheng.model.SaleOrder;
import org.springframework.stereotype.Component;

@Component
public interface SaleOrderMapper {

    void addOrder(SaleOrder order);
}
