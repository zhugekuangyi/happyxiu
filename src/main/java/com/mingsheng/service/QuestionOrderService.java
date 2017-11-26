package com.mingsheng.service;

import com.mingsheng.mapper.QuestionOrderMapper;
import com.mingsheng.model.QuestionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionOrderService {

    @Autowired
    private QuestionOrderMapper mapper;


    public void insert(QuestionOrder order){
        mapper.insert(order);
    }

}
