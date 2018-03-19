package com.mingsheng.service;

import com.mingsheng.mapper.RepairResultMapper;
import com.mingsheng.model.RepairResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairResultService {
    @Autowired
    private RepairResultMapper mapper;


    public RepairResult getResultById(String questionId, String mobileId) {
        return mapper.getResultById(questionId,mobileId);
    }
}
