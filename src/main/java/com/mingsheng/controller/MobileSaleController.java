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
@RequestMapping(value = "mobileSale")
public class MobileSaleController {
    @Autowired
    private MobileSaleService mobileSaleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private MobileTypeService mobileTypeService;
    @Autowired
    private SaleOrderService saleOrderService;

    @ResponseBody
    @RequestMapping(value = "getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getList(HttpServletRequest request, HttpServletResponse response){
        List list =null;
        try {
            list = mobileSaleService.getList();
            return RespStatus.success().element("list",list);
        } catch (Exception e) {
            e.printStackTrace();

            return RespStatus.fail("获取列表失败");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject insert(HttpServletRequest request,String token,String mobileId,Integer status,String addressId,String remark){

        try {
            if(token==null || token.length()<=0){
                return RespStatus.fail("token不能为空");
            }
            if(mobileId ==null || mobileId.length()<=0){
                return RespStatus.fail("回收的手机型号不能为空");
            }
            if(status==null){
                return RespStatus.fail("请填写送货方式");
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
            MobileSale mobileSale = mobileSaleService.getInfoById(mobileId);
            if(mobileSale ==null){
                return RespStatus.fail("该商品不存在");
            }

            SaleOrder order = new SaleOrder();
            order.setId(MyUUID.getUUID());
            order.setAddress(userAddress.getAddress());
            order.setCtime(new Timestamp(System.currentTimeMillis()));
            order.setName(userAddress.getName());
            MobileType mobileName = mobileTypeService.getInfoById(mobileSale.getMobileName());
            MobileType mobileType = mobileTypeService.getInfoById(mobileSale.getMobileType());
            order.setMobileName(mobileName.getName());
            order.setMobileType(mobileType.getName());
            MobileType mobileMemory = mobileTypeService.getInfoById(mobileSale.getMemory());
            MobileType mobileColour = mobileTypeService.getInfoById(mobileSale.getColour());
            order.setMobileColour(mobileColour.getName());
            order.setMobileMemory(mobileMemory.getName());
            order.setOrderNo(StringUtil.getOrderNum());
            order.setOrderStatus(status);
            order.setPhone(userAddress.getPhone());
            order.setUserId(user.getId());
            if(remark==null || remark.length()<=0){
                order.setRemark("");
            }else {
                order.setRemark(remark);
            }

            saleOrderService.insert(order);

            return RespStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail("订单增加错误");
        }



    }

    @RequestMapping(value = "/getPrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getPrice(HttpServletRequest request,String mobileType,String mobileName,String mobileMemory,String mobileColour){
        MobileSale recovery=null;

        try {
            if(mobileType==null || mobileType.length()<=0){
                return RespStatus.fail("mobileType不能为空");
            }

            if(mobileName==null || mobileName.length()<=0){
                return RespStatus.fail("mobileName不能为空");
            }

            if(mobileMemory ==null || mobileMemory.length()<=0){
                return RespStatus.fail("mobileMemory不能为空");
            }
            if(mobileColour==null || mobileColour.length()<=0){
                return RespStatus.fail("mobileColour不能为空");
            }

            recovery = mobileSaleService.getInfo(mobileType,mobileName,mobileMemory,mobileColour);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(recovery!=null){
            return RespStatus.success().element("sale",recovery);
        }else {
            return RespStatus.fail("手机型号不支持购买");
        }
    }


}
