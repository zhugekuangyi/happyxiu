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
     * 删除地址
     * @param addrId
     * @return
     */
    @RequestMapping(value = "/delAddr", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject delAddr(String addrId) {
        try {
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


}
