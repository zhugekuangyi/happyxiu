package com.mingsheng.service;

import com.mingsheng.mapper.RepairResultMapper;
import com.mingsheng.model.RepairResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairResultService {
    @Autowired
    private RepairResultMapper mapper;


    public RepairResult getResultById(String questionId, String mobileId) {
        return mapper.getResultById(questionId,mobileId);
    }

    public List<RepairResult> ListNoPage(String questionType) {

        return mapper.getListNoPage(questionType);
    }

    public void update(String result, String price, Integer id) {
        mapper.update(result,price,id);
    }
}
