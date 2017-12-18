package com.mingsheng.service;

import com.mingsheng.mapper.StoreAddressMapper;
import com.mingsheng.model.StoreAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreAddressService {

    @Autowired
    private StoreAddressMapper mapper;


    public void insert(StoreAddress address){
        mapper.insert(address.getId(),address.getAddress(),address.getRemark(),address.getImg(),address.getTitle(),address.getCtime());
    }

    public List<StoreAddress> getStoreAddress(){
        return mapper.getStoreAddress();
    }

    public void del(String id) {
        mapper.del(id);
    }
}
