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

    public List getList(Integer page,Integer limit){
        List<MobileSale> list = mapper.getList(page,limit);
        List<MobileSale> list1 = new ArrayList<>();
        for (MobileSale m:list) {
            MobileSale mobileSale = new MobileSale();
            mobileSale.setId(m.getId());
            mobileSale.setMobileColour(m.getMobileColour());
            mobileSale.setMobileMemory(m.getMobileMemory());
            mobileSale.setMobileName(m.getMobileName());
            mobileSale.setMobileType(m.getMobileType());
            mobileSale.setPrice(m.getPrice());
            mobileSale.setCtime(m.getCtime());
            if(m.getImg()==null || m.getImg().length()<=0){
              mobileSale.setImg(ImgUtils.defaultUrl);
            }else {
                mobileSale.setImg(ImgUtils.imgUrl+m.getImg());
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
