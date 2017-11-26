package com.mingsheng.mapper;

import com.mingsheng.model.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionMapper {

    List<Question> getListByType(@Param(value = "type") Integer type);

    Question selectResultById(@Param(value = "id") String questionId);
}
