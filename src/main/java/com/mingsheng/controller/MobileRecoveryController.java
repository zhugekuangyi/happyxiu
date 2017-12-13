package com.mingsheng.controller;

import com.mingsheng.model.*;
import com.mingsheng.service.*;
import com.mingsheng.utils.MyUUID;
import com.mingsheng.utils.RespStatus;
import com.mingsheng.utils.StringUtil;
import com.mingsheng.utils.TokenUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(value = "mobileRecovery")
public class MobileRecoveryController {

    @Autowired
    private MobileRecoveryService mobileRecoveryService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private RecoveryOrderService recoveryOrderService;
    @Autowired
    private MobileTypeService mobileTypeService;
    @Autowired
    private CodeService codeService;


    /**
     * 回收首页得列表
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getMobileRecoveyList(HttpServletRequest request, HttpServletResponse response,Integer page){
        List list = null;
        try {
            list = mobileRecoveryService.getList(page,10);
            return RespStatus.success().element("list",list);
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail("获取回收列表失败");
        }

    }


    @ResponseBody
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject insert(HttpServletRequest request,String token,String mobileId,Integer status,String addressId,String remark,String phone,String code){

        try {
            if(token==null || token.length()<=0){
                return RespStatus.fail("token不能为空");
            }
            if(mobileId ==null || mobileId.length()<=0){
                return RespStatus.fail("回收的手机型号不能为空");
            }
            if(status==null){
                return RespStatus.fail("请填写回收方式");
            }
            if(addressId==null || addressId.length()<=0){
                return RespStatus.fail("地址不能为空");
            }
            User user = userService.getUserById(TokenUtil.getId(token));
            if(user ==null){
                return RespStatus.fail("该用户不存在");
            }
            UserAddress userAddress = userAddressService.getAddById(addressId);
            if(userAddress==null){
                return RespStatus.fail("地址不存在");
            }
            MobileRecovery recovery = mobileRecoveryService.getInfoById(mobileId);
            if(recovery ==null){
                return RespStatus.fail("该回收商品不存在");
            }
            if (code == null || code.trim().length() <= 0) {
                return RespStatus.fail("验证码不能为空！");
            }
            if(phone==null || phone.length()<=0){
                return RespStatus.fail("手机号码不能为空");
            }
            Code code1 = codeService.getCode(phone);
            if(code1==null){
                return RespStatus.fail("验证码错误");
            }
            if(code.equals(code1.getCode())){
                codeService.delCode(phone);
            }else {
                return RespStatus.fail("验证码错误");
            }

            RecoveryOrder order = new RecoveryOrder();
            order.setId(MyUUID.getUUID());
            order.setAddress(userAddress.getAddress());
            order.setCtime(new Timestamp(System.currentTimeMillis()));
            order.setName(userAddress.getName());
            MobileType mobileName = mobileTypeService.getInfoById(recovery.getMobileName());
            MobileType mobileType = mobileTypeService.getInfoById(recovery.getMobileType());
            order.setMobileName(mobileName.getName());
            order.setMobileType(mobileType.getName());
            order.setOrderNo(StringUtil.getOrderNum());
            order.setOrderStatus(status);
            order.setPhone(phone);
            order.setPrice(recovery.getPrice());
            order.setUserId(user.getId());
            order.setImg(recovery.getImg());
            if(remark==null || remark.length()<=0){
                order.setRemark("");
            }else {
                order.setRemark(remark);
            }

            recoveryOrderService.insert(order);

            return RespStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail("订单增加错误");
        }



    }

    @RequestMapping(value = "/getPrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getPrice(HttpServletRequest request,String mobileType,String mobileName){
        MobileRecovery recovery=null;

        try {
            if(mobileType==null || mobileType.length()<=0){
                return RespStatus.fail("mobileType不能为空");
            }

            if(mobileName==null || mobileName.length()<=0){
                return RespStatus.fail("mobileName不能为空");
            }

            recovery = mobileRecoveryService.getInfo(mobileType,mobileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(recovery!=null){
            return RespStatus.success().element("recovery",recovery);
        }else {
            return RespStatus.fail("手机型号不支持回收");
        }
    }

}
