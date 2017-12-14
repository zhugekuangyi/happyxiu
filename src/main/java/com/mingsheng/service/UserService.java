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

    public User getUserByPhone(String phone){
        User user = userMapper.getUserByPhone(phone);
        return user;
    }

    public Integer savaUser(String id, String phone, String pwd, Timestamp time, String nickName,String img){
        Integer integer = userMapper.savaUser(id, phone, pwd, time, nickName,img);
        return integer;
    }

    public User getUserById(String id){
        User user = userMapper.getUserById(id);

        return user;
    }

    public void updatePad(String phone,String password){
        userMapper.updateByPhone(phone,password);
    }
}
