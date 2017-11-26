package com.mingsheng.service;

import com.mingsheng.mapper.SaleOrderMapper;
import com.mingsheng.model.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleOrderService {

    @Autowired
    private SaleOrderMapper mapper;


    public void insert(SaleOrder order){
        mapper.addOrder(order);
    }
}
