package com.mingsheng.mapper;

import com.mingsheng.model.RepairResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface RepairResultMapper {

    RepairResult getResultById(@Param(value = "questionId") String questionId,
                               @Param(value = "mobileId") String mobileId);
}
