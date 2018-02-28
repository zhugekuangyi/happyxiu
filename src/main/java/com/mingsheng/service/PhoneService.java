package com.mingsheng.service;

import com.mingsheng.mapper.PhoneMapper;
import com.mingsheng.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    @Autowired
    private PhoneMapper phoneMapper;

    public String getPhone(String s) {
        return phoneMapper.getPhone(s);
    }

    public List<Phone> getPhoneList() {
        return phoneMapper.getPhoneList();
    }

    public void updatePhone(String id, String phone) {
        phoneMapper.updatePhone(id,phone);
    }
}
