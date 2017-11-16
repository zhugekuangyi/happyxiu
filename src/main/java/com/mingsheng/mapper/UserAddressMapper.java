package com.mingsheng.mapper;

import com.mingsheng.model.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserAddressMapper {

    List<UserAddress> listByUserId(@Param(value = "userId") String userId);

    void updateDef(@Param(value = "id") String id, @Param(value = "def") Integer def);

    UserAddress getById(@Param(value = "id") String id);

    void delete(@Param(value = "id") String addId);

    UserAddress getDefaultAddr(@Param(value = "userId") String userId);
}
