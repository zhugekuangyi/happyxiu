package com.mingsheng.mapper;


import com.mingsheng.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
@Component
public interface UserMapper {
    User getUserByPhone(@Param(value = "phone") String phone);

    Integer savaUser(@Param(value = "id") String id,
                     @Param(value = "phone") String phone,
                     @Param(value = "password") String pwd,
                     @Param(value = "ctime") Timestamp time,
                     @Param(value = "nickname") String nickName,
                     @Param(value = "img") String img);

    User getUserById(@Param(value = "userId") String id);

    void updateByPhone(@Param(value = "phone") String phone,@Param(value = "password") String password);

    List<User> getUserList();
}
