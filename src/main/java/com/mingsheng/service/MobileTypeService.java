package com.mingsheng.service;

import com.mingsheng.mapper.MobileTypeMapper;
import com.mingsheng.model.MobileType;
import com.mingsheng.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public void saveMobile(Integer status, String mobileType, Integer i,String pid) {
        String id = MathUtil.getId();
        MobileType infoById = mobileTypeMapper.getInfoById(id);
        while (infoById!=null){
            id=MathUtil.getId();
        }
        Timestamp time = new Timestamp(System.currentTimeMillis());
        MobileType mt = new MobileType();
        mt.setId(id);
        mt.setCtime(time);
        mt.setLevei(i);
        mt.setName(mobileType);
        mt.setPid(pid);
        mt.setStatus(status);
        mobileTypeMapper.insert(mt);
    }
}
