package com.mingsheng.service;

import com.mingsheng.mapper.QuestionMapper;
import com.mingsheng.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper mapper;

    public List<Question> getListByType(Integer type){
        List<Question> list = mapper.getListByType(type);
        return list;

    }

    public Question selectResultById(String questionId) {
        return mapper.selectResultById(questionId);
    }
}
