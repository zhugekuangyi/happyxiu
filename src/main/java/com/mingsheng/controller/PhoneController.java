package com.mingsheng.controller;

import com.mingsheng.model.Phone;
import com.mingsheng.service.PhoneService;
import com.mingsheng.utils.RespStatus;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;


    @RequestMapping(value = "/getOrderPhone",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getOrderPhone(){

        try {
            String phone = phoneService.getPhone("1");
            if(phone!=null && phone!=""){
                return RespStatus.success().element("phone",phone);
            }else {
                return RespStatus.success().element("phone","13685753795");
            }


        }catch (Exception e){
            return RespStatus.fail("获取失败，请重试！");
        }

    }

    @RequestMapping(value = "/getkfPhone",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getkfPhone(){

        try {
            String phone = phoneService.getPhone("2");
            if(phone!=null && phone!=""){
                return RespStatus.success().element("phone",phone);
            }else {
                return RespStatus.success().element("phone","13685753795");
            }


        }catch (Exception e){
            return RespStatus.fail("获取失败，请重试！");
        }

    }

    @RequestMapping(value = "/getxdPhone",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getxdPhone(){

        try {
            String phone = phoneService.getPhone("3");
            if(phone!=null && phone!=""){
                return RespStatus.success().element("phone",phone);
            }else {
                return RespStatus.success().element("phone","13685753795");
            }


        }catch (Exception e){
            return RespStatus.fail("获取失败，请重试！");
        }

    }

    @RequestMapping(value = "/getPhoneList",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getPhoneList(){
        try {
            List<Phone> phone = phoneService.getPhoneList();

            List<Map<String,Object>> list = new ArrayList<>();
            for (Phone p:phone) {
                Map<String,Object> map = new HashMap<>();
                map.put("id",p.getId());
                map.put("phone",p.getPhone());
                if("1".equals(p.getId())){
                    map.put("name","一键下单电话");
                }else if("2".equals(p.getId())){
                    map.put("name","客服电话");
                }else {
                    map.put("name","下单通知电话");
                }
                list.add(map);
            }
            return RespStatus.success().element("list",list);

        }catch (Exception e){
            return RespStatus.fail("获取失败，请重试！");
        }
    }

    @RequestMapping(value = "/updatePhone",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject updatePhone(@RequestParam(value = "id") String id, @RequestParam(value = "phone") String phone){

        try {
            if(id==null||id==""){
                return RespStatus.fail("id不能为空！");
            }
            if(phone==null || phone==""){
                return RespStatus.fail("phone不能为空");
            }
            phoneService.updatePhone(id,phone);

            return RespStatus.success();

        }catch (Exception e){
            return RespStatus.fail("获取失败");
        }
    }


}
