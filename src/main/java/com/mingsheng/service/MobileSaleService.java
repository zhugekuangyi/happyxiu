package com.mingsheng.service;

import com.mingsheng.mapper.MobileSaleMapper;
import com.mingsheng.mapper.MobileTypeMapper;
import com.mingsheng.model.MobileSale;
import com.mingsheng.model.MobileType;
import com.mingsheng.utils.ImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileSaleService {

    @Autowired
    private MobileSaleMapper mapper;
    @Autowired
    private MobileTypeMapper mobileTypeMapper;

    public List getList(){
        List<MobileSale> list = mapper.getList();
        List<MobileSale> list1 = new ArrayList();
        for (MobileSale ms:list) {
            MobileSale mobileSale = new MobileSale();
            mobileSale.setId(ms.getId());
            MobileType mobileType = mobileTypeMapper.getInfoById(mobileSale.getMobileType());
            MobileType mobileName = mobileTypeMapper.getInfoById(mobileSale.getMobileName());
            mobileSale.setMobileName(mobileName.getName());
            mobileSale.setMobileType(mobileType.getName());
            MobileType mobileMemory = mobileTypeMapper.getInfoById(mobileSale.getMemory());
            mobileSale.setMemory(mobileMemory.getName());
            MobileType mobileColour = mobileTypeMapper.getInfoById(mobileSale.getColour());
            mobileSale.setColour(mobileColour.getName());
            mobileSale.setPrice(mobileSale.getPrice());
            if(mobileSale.getImg()==null || mobileSale.getImg().length()<=0){
                mobileSale.setImg(ImgUtils.defaultUrl);
            }else {
                mobileSale.setImg(ImgUtils.imgUrl+mobileSale.getImg());
            }
            list1.add(mobileSale);
        }
        return list1;
    }


    public MobileSale getInfo(String pid, String id,String mobileMemory,String mobileColour) {
        MobileSale sale = mapper.getInfo(pid,id,mobileMemory,mobileColour);
        return sale;
    }

    public MobileSale getInfoById(String mobileId) {

        MobileSale recovery = mapper.getInfoById(mobileId);

        return recovery;
    }
}
