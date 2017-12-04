package com.mingsheng.controller;

import com.mingsheng.utils.RespStatus;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "mine")
public class CommController {

    @RequestMapping(value = "/getMobileList")
    @ResponseBody
    public JSONObject getListByUserId(String token){
        if(token==null || token.length()<=0){
            return RespStatus.fail("token不能为空");
        }
    }
}
