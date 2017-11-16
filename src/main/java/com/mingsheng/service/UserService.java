package com.mingsheng.service;

import com.mingsheng.mapper.UserMapper;
import com.mingsheng.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByPhone(Integer phone){
        User user = userMapper.getUserByPhone(phone);
        return user;
    }

    public Integer savaUser(String id, Integer phone, String pwd, Timestamp time, String nickName ){
        Integer integer = userMapper.savaUser(id, phone, pwd, time, nickName);
        return integer;
    }
}
