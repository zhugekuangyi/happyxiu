package com.mingsheng.service;

import com.mingsheng.mapper.MobileTypeMapper;
import com.mingsheng.model.MobileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileTypeService {
    @Autowired
    private MobileTypeMapper mobileTypeMapper;

    public List<MobileType> getList(){

        return mobileTypeMapper.getList();

    }


    public List<MobileType> getListByPid(String id){
        return mobileTypeMapper.getListByPid(id);
    }
}
