package com.mingsheng.controller;

import com.mingsheng.service.SysUserService;
import com.mingsheng.utils.RespStatus;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sys_user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(String name,String password){
        if(name==null || name.length()<=0){
            return RespStatus.fail("账号不能为空");
        }
        if(password==null || password.length()<=0){
            return RespStatus.fail("密码不能为空");
        }
        boolean login = sysUserService.login(name, password);
        if(login){
            return RespStatus.success();
        }else {
            return RespStatus.fail("账号或密码错误!");
        }

    }

}
