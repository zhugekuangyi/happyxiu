package com.mingsheng.mapper;

import com.mingsheng.model.QuestionOrder;
import org.springframework.stereotype.Component;

@Component
public interface QuestionOrderMapper {

    void insert(QuestionOrder order);
}
