package com.mingsheng.mapper;

import com.mingsheng.model.QuestionOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionOrderMapper {

    void insert(QuestionOrder order);

    List<QuestionOrder> getListByUserId(@Param(value = "userId") String id);

    QuestionOrder getOrderById(@Param(value = "id") String orderId);

    List<QuestionOrder> getList();
}
