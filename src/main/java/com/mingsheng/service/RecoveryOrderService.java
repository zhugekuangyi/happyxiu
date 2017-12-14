package com.mingsheng.service;

import com.mingsheng.mapper.RecoveryOrderMapper;
import com.mingsheng.model.RecoveryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoveryOrderService {

    @Autowired
    private RecoveryOrderMapper recoveryOrderMapper;


    public void insert(RecoveryOrder order){
        recoveryOrderMapper.addOrder(order);
    }

    public List<RecoveryOrder> getListByUserId(String id) {

        return recoveryOrderMapper.getListByUserId(id);
    }

    public RecoveryOrder getOrderById(String orderId) {
        return recoveryOrderMapper.getOrderById(orderId);
    }
}
