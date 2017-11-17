package com.mingsheng.service;

import com.mingsheng.mapper.UserAddressMapper;
import com.mingsheng.model.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

        public List<UserAddress> listByUserId(String userId){
            List<UserAddress> userAddresses = userAddressMapper.listByUserId(userId);
            return userAddresses;
        }

        public void isDef(String id,String userId){
            List<UserAddress> list = listByUserId(userId);
            for (UserAddress userAddress: list) {
                if(userAddress.getDef()==1){
                    userAddressMapper.updateDef(userAddress.getId(),0);
                }else {
                    if(userAddress.getId().equals(id)){
                        userAddressMapper.updateDef(id,1);
                    }
                }
            }

        }

        public UserAddress getAddById(String addId){
            UserAddress address = userAddressMapper.getById(addId);
            return address;
        }

        public void delete(String addId){
            userAddressMapper.delete(addId);
        }

        public void updateDef(String id,Integer def){
            userAddressMapper.updateDef(id,def);
        }

        public UserAddress getDefaultAddr(String userId){
            UserAddress address =  userAddressMapper.getDefaultAddr(userId);
            return address;
        }

    public void saveOrupdateAdd(UserAddress userAddress) {

            if(userAddress.getId()!="" && userAddress.getId()!=null){
                userAddressMapper.update(userAddress);
            }
    }
}
