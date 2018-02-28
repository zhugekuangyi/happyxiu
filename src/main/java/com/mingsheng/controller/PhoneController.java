package com.mingsheng.controller;

import com.mingsheng.service.PhoneService;
import com.mingsheng.utils.RespStatus;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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



}
