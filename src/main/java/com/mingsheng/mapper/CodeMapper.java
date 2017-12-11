package com.mingsheng.mapper;

import com.mingsheng.model.Code;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CodeMapper {

    Code getCodeByphone(String phone);

    void del(String phone);

    void add(@Param(value = "phone") String phone,
             @Param(value = "code") String code);

    void update(@Param(value = "phone") String phone,
                @Param(value = "code") String code);
}
