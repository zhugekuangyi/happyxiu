package com.mingsheng.service;

import com.mingsheng.mapper.PhoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {
    @Autowired
    private PhoneMapper phoneMapper;

    public String getPhone(String s) {
        return phoneMapper.getPhone(s);
    }
}
