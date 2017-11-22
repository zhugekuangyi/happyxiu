package com.mingsheng.service;

import com.mingsheng.mapper.MobileRecoveryMapper;
import com.mingsheng.model.MobileRecovery;
import com.mingsheng.utils.ImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileRecoveryService {

    @Autowired
    private MobileRecoveryMapper mapper;

    public List getList(){
        List<MobileRecovery> list = mapper.getList();

        List<MobileRecovery> list1 = new ArrayList();
        for (MobileRecovery mr:list) {
            MobileRecovery mobileRecovery = new MobileRecovery();
            mobileRecovery.setId(mr.getId());
            mobileRecovery.setName(mr.getName());
            mobileRecovery.setPrice(mr.getPrice());
            if(mr.getImg()==null || mr.getImg().length()<=0){
                mobileRecovery.setImg(ImgUtils.defaultUrl);
            }else {
                mobileRecovery.setImg(ImgUtils.imgUrl+mr.getImg());
            }


        }
        return list;
    }


}
