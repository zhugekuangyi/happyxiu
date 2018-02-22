package com.mingsheng.service;

import com.mingsheng.mapper.MobileRecoveryMapper;
import com.mingsheng.mapper.MobileTypeMapper;
import com.mingsheng.model.MobileRecovery;
import com.mingsheng.model.MobileType;
import com.mingsheng.utils.ImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileRecoveryService {

    @Autowired
    private MobileRecoveryMapper mapper;
    @Autowired
    private MobileTypeMapper mobileTypeMapper;

    public List getList(Integer page,Integer limit){
        List<MobileRecovery> list = mapper.getList(page,limit);
        List<MobileRecovery> list1 = new ArrayList();
        for (MobileRecovery mr:list) {
            MobileRecovery mobileRecovery = new MobileRecovery();
            mobileRecovery.setId(mr.getId());
            MobileType mobileType = mobileTypeMapper.getInfoById(mr.getMobileType());
            MobileType mobileName = mobileTypeMapper.getInfoById(mr.getMobileName());
            mobileRecovery.setMobileName(mobileName.getName());
            mobileRecovery.setMobileType(mobileType.getName());
            mobileRecovery.setPrice(mr.getPrice());
            if(mr.getImg()==null || mr.getImg().length()<=0){
                mobileRecovery.setImg(ImgUtils.defaultUrl);
            }else {
                mobileRecovery.setImg(ImgUtils.imgUrl+mr.getImg());
            }
            mobileRecovery.setCtime(mr.getCtime());
            list1.add(mobileRecovery);
        }
        return list1;
    }


    public MobileRecovery getInfo(String pid, String id) {
        MobileRecovery recovery = mapper.getInfo(pid,id);
        return recovery;
    }

    public MobileRecovery getInfoById(String mobileId) {

        MobileRecovery recovery = mapper.getInfoById(mobileId);

        return recovery;
    }

    public List ListNoPage() {
        List<MobileRecovery> list = mapper.getListNoPage();
        List<MobileRecovery> list1 = new ArrayList();
        for (MobileRecovery mr:list) {
            MobileRecovery mobileRecovery = new MobileRecovery();
            mobileRecovery.setId(mr.getId());
            MobileType mobileType = mobileTypeMapper.getInfoById(mr.getMobileType());
            MobileType mobileName = mobileTypeMapper.getInfoById(mr.getMobileName());
            mobileRecovery.setMobileName(mobileName.getName());
            mobileRecovery.setMobileType(mobileType.getName());
            mobileRecovery.setPrice(mr.getPrice());
            if(mr.getImg()==null || mr.getImg().length()<=0){
                mobileRecovery.setImg(ImgUtils.defaultUrl);
            }else {
                mobileRecovery.setImg(ImgUtils.imgUrl+mr.getImg());
            }
            mobileRecovery.setCtime(mr.getCtime());
            list1.add(mobileRecovery);
        }
        return list1;
    }

    public void del(String id) {
        mapper.del(id);
    }
}
