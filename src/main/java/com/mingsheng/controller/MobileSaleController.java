package com.mingsheng.controller;

import com.mingsheng.model.*;
import com.mingsheng.service.*;
import com.mingsheng.utils.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CodeService codeService;
    @Autowired
    private PhoneService phoneService;

    @ResponseBody
    @RequestMapping(value = "getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getList(HttpServletRequest request, HttpServletResponse response,Integer page){
        List list =null;
        try {
            list = mobileSaleService.getList(page,10);
            return RespStatus.success().element("list",list);
        } catch (Exception e) {
            e.printStackTrace();

            return RespStatus.fail("获取列表失败");
        }

    }


    @ResponseBody
    @RequestMapping(value = "getListNoPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getListNoPage(HttpServletRequest request, HttpServletResponse response){
        List<RepairResult> list =null;
        try {
            list = mobileSaleService.ListNoPage();
            return RespStatus.success().element("list",list);
        } catch (Exception e) {
            e.printStackTrace();

            return RespStatus.fail("获取列表失败");
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
                return RespStatus.fail("出售的手机型号不能为空");
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

            SaleOrder order = new SaleOrder();
            order.setId(MyUUID.getUUID());
            order.setAddress(userAddress.getAddress());
            order.setCtime(new Timestamp(System.currentTimeMillis()));
            order.setName(userAddress.getName());
            order.setMobileName(mobileSale.getMobileName());
            order.setMobileType(mobileSale.getMobileType());
            order.setMobileColour(mobileSale.getMobileColour());
            order.setMobileMemory(mobileSale.getMobileMemory());
            order.setOrderNo(StringUtil.getOrderNum());
            order.setOrderStatus(status);
            order.setPhone(phone);
            order.setPrice(mobileSale.getPrice());
            order.setUserId(user.getId());
            if(remark==null || remark.length()<=0){
                order.setRemark("");
            }else {
                order.setRemark(remark);
            }

            saleOrderService.insert(order);
            String phone1 = phoneService.getPhone("3");
            if(phone1==null || phone1 ==""){
                phone1="13685753795";
            }
            SmsUtils.veriOrder(phone1,phone,userAddress.getAddress(),"维修",mobileSale.getMobileType()+"/"+mobileSale.getMobileName()+"/"+mobileSale.getMobileColour()+"/"+mobileSale.getMobileMemory());

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

    @RequestMapping(value = "/createMobile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject createMobile(@RequestParam(value = "mobileType") String mobileType,
                                   @RequestParam(value = "mobileName") String mobileName,
                                   @RequestParam(value = "mobileMemory") String mobileMemory,
                                   @RequestParam(value = "mobileColour") String mobileColour,
                                   @RequestParam(value = "price") String price,
                                   @RequestParam(value = "img") String img){

        try {


        if(img==null || img.trim().length()<0){
            return RespStatus.fail("图片不能为空");
        }
        if(mobileName==null || mobileName.trim().length()<=0){
            return RespStatus.fail("手机型号不能空");
        }
        if(mobileType==null || mobileType.trim().length()<=0){
            return RespStatus.fail("手机厂商不能空");
        }
        if(mobileMemory==null || mobileMemory.trim().length()<=0){
            return RespStatus.fail("手机内存不能空");
        }
        if(mobileColour==null || mobileColour.trim().length()<=0){
            return RespStatus.fail("手机颜色不能空");
        }
        if(price==null || price.trim().length()<=0){
            return RespStatus.fail("价格不能空");
        }
        MobileSale mobileSale = new MobileSale();
        String id = MathUtil.getId();
            MobileSale infoById = mobileSaleService.getInfoById(id);
            while(infoById!=null){
                id=MathUtil.getId();
            }
            mobileSale.setId(id);
        mobileSale.setMobileColour(mobileColour);
        mobileSale.setMobileName(mobileName);
        mobileSale.setMobileType(mobileType);
        mobileSale.setMobileMemory(mobileMemory);
        mobileSale.setCtime(new Timestamp(System.currentTimeMillis()));
        mobileSale.setPrice(price);

        Map<String, String> map = OSSUtil.baseToFile(img);
        if("0".equals(map.get("code"))){
            mobileSale.setImg(map.get("fileName"));
        }else {
            mobileSale.setImg("");
        }
        mobileSaleService.insert(mobileSale);
            return RespStatus.success();
        }catch (Exception e){
            return RespStatus.fail("添加失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONObject delStore(@RequestParam(value = "id") String id){
        try {
            mobileSaleService.del(id);
            return RespStatus.success();
        }catch (Exception e){
            e.printStackTrace();
            return RespStatus.fail("删除失败");
        }
    }

    @RequestMapping(value = "getSaleList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getList(@RequestParam(value = "phone") String phone){
        List<Map<String,Object>> list=new ArrayList<>();
        try {
            List<SaleOrder> orders = saleOrderService.getList(phone);
            for (SaleOrder order:orders) {
                Map<String,Object> map = new HashMap<>();
                map.put("orderNo",order.getOrderNo());
                map.put("name",order.getName());
                map.put("phone",order.getPhone());
                map.put("mobileType",order.getMobileType());
                map.put("mobileName",order.getMobileName());
                map.put("mobileColour",order.getMobileColour());
                map.put("mobileMemory",order.getMobileMemory());
                map.put("address",order.getAddress());
                map.put("price",order.getPrice());
                map.put("time",order.getCtime().toString());
                map.put("remark",order.getRemark());
                if(0==order.getOrderStatus()){
                    map.put("orderStatus","到店购买");
                }else if(1==order.getOrderStatus()){
                    map.put("orderStatus","送货上门");
                }else {
                    map.put("orderStatus","快递");
                }

                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespStatus.fail();
        }

        return RespStatus.success().element("list",list);
    }


}
