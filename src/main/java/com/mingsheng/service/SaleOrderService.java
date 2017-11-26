package com.mingsheng.service;

import com.mingsheng.mapper.RecoveryOrderMapper;
import com.mingsheng.model.RecoveryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleOrderService {

    @Autowired
    private RecoveryOrderMapper recoveryOrderMapper;


    public void insert(RecoveryOrder order){
        recoveryOrderMapper.addOrder(order);
    }
}
