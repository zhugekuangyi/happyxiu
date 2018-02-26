package com.mingsheng.service;

import com.mingsheng.mapper.SaleOrderMapper;
import com.mingsheng.model.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOrderService {

    @Autowired
    private SaleOrderMapper mapper;


    public void insert(SaleOrder order){
        mapper.addOrder(order);
    }


    public List<SaleOrder> getListByUserId(String userId){
        return mapper.getListByUserId(userId);
    }

    public SaleOrder getOrderById(String orderId){
        return mapper.getOrderById(orderId);
    }

    public List<SaleOrder> getList(String phone) {
        return mapper.getList(phone);
    }
}
