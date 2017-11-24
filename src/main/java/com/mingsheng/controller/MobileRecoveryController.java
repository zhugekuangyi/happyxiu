package com.mingsheng.controller;

import com.mingsheng.service.MobileRecoveryService;
import com.mingsheng.utils.RespStatus;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "mobileRecovery")
public class MobileRecoveryController {

    @Autowired
    private MobileRecoveryService mobileRecoveryService;

    @ResponseBody
    @RequestMapping(value = "getList")
    public JSONObject getMobileRecoveyList(HttpServletRequest request, HttpServletResponse response){
        List list = null;
        try {
            list = mobileRecoveryService.getList();
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail("获取回收列表失败");
        }
        return RespStatus.success().element("list",list);
    }
}
