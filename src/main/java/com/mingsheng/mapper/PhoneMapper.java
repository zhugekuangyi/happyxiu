package com.mingsheng.mapper;

import com.mingsheng.model.Phone;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhoneMapper {


    String getPhone(@Param(value = "id") String s);

    List<Phone> getPhoneList();

    void updatePhone(@Param(value = "id") String id,@Param(value = "phone") String phone);
}
