package com.mingsheng.controller;

import com.mingsheng.model.UserAddress;
import com.mingsheng.service.UserAddressService;
import com.mingsheng.utils.RespStatus;
import com.mingsheng.utils.TokenUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "userAddress")
@Controller
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;


    /**
     * 地址列表
     * @param token
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject listByUserId(String token){
        try {
            if(token==null || token.trim().length()<=0){
                return RespStatus.fail("token不能为空");
            }
        List<UserAddress> list = userAddressService.listByUserId(TokenUtil.getId(token));
            return RespStatus.success().element("list",list);
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }
    }

    /**
     * 根据地址id把地址设为默认
     * @param token
     * //是否默认（0：否，1：是）
     * @return
     */
    @RequestMapping(value = "/setDefaultAddr", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject setDefaultAddr(String token,String addrId) {
        try {
            if (token == null || token.trim().length() <= 0) {
                return RespStatus.fail("token不能为空");
            }
            if (addrId == null || addrId.trim().length() <= 0) {
                return RespStatus.fail("地址addrId不能为空");
            }
            userAddressService.isDef(addrId,TokenUtil.getId(token));
            return  RespStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail();
        }
    }

    /**
     * d
     * @param addrId
     * @return
     */
    @RequestMapping(value = "/delAddr", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject delAddr(String addrId,String token) {
        try {
            if(token ==null || token.length()<=0){
                return RespStatus.fail("token不能为空");
            }
            if (addrId == null || addrId.trim().length() <= 0) {
                return RespStatus.fail("地址addrId不能为空");
            }
            UserAddress address = userAddressService.getAddById(addrId);
            userAddressService.delete(addrId);
            //如果删除的地址是默认地址
            if(address.getDef()==1){
                List<UserAddress> userAddresses = userAddressService.listByUserId(address.getUserId());
                //用户还有其他地址
                if(userAddresses.size()>0){
                    //把最新的地址设为默认
                    userAddressService.updateDef(userAddresses.get(0).getId(),1);
                }
            }
            return  RespStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespStatus.fail();
    }

    /**
     * 获取用户默认地址
     * @param token
     * @return
     */
    @RequestMapping(value = "/defaultAddr", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject defaultAddr(String token) {
        try {
            if (token == null || token.trim().length()<=0){
                return  RespStatus.fail("token不能为空");
            }
            UserAddress address = userAddressService.getDefaultAddr(TokenUtil.getId(token));
            if(address == null ){
                List<UserAddress> userAddresses = userAddressService.listByUserId(TokenUtil.getId(token));
                if(userAddresses.size() > 0){
                    return RespStatus.success().element("data",userAddresses.get(0));
                }else{
                    return RespStatus.success().element("data",new UserAddress());
                }
            }else{
                return RespStatus.success().element("data",address);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail();
        }
    }


    @RequestMapping(value = "/saveOrUpdateAddr", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject saveOrupdateAdd(String token ,String name,String phone,String address,String addId){
        try {
            if (token == null || token.trim().length() <= 0) {
                return RespStatus.fail("token不能为空");
            }
            if (name == null || name.trim().length() <= 0) {
                return RespStatus.fail("name不能为空");
            }
            if (phone == null || phone.trim().length() <= 0) {
                return RespStatus.fail("phone不能为空");
            }
            if(address ==null || address.trim().length()<=0){
                return RespStatus.fail("地址不能为空");
            }

            UserAddress userAddress = new UserAddress();

            userAddress.setId(addId);
            userAddress.setAddress(address);
            userAddress.setName(name);
            userAddress.setPhone(phone);
            userAddress.setUserId(TokenUtil.getId(token));
            userAddressService.saveOrupdateAdd(userAddress);


        }catch (Exception e) {
            e.printStackTrace();
            return  RespStatus.fail();
        }

        return null;
    }

}
