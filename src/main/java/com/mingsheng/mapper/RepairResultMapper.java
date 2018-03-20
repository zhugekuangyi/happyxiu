package com.mingsheng.mapper;

import com.mingsheng.model.RepairResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RepairResultMapper {

    RepairResult getResultById(@Param(value = "questionId") String questionId,
                               @Param(value = "mobileId") String mobileId);

    List<RepairResult> getListNoPage(@Param(value = "questionType") String questionType);

    void update(@Param(value = "result") String result,
                @Param(value = "price") String price,
                @Param(value = "id") Integer id);
}
