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

    public List<MobileType> getList(Integer status){

        return mobileTypeMapper.getList(status);

    }


    public List<MobileType> getListByPid(String id){
        return mobileTypeMapper.getListByPid(id);
    }


    public MobileType getInfoById(String id){

        return mobileTypeMapper.getInfoById(id);
    }

    public List<MobileType> getMobileList() {

        return mobileTypeMapper.getMobileList();
    }

    public void del(String id) {
        mobileTypeMapper.del(id);
    }
}
