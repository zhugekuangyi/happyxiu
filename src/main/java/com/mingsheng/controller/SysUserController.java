package com.mingsheng.controller;

import com.mingsheng.utils.RespStatus;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys_user")
public class SysUserController {


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(String name,String password){
        System.out.println(name);
        System.out.println(password);
        return RespStatus.success();
    }

}
