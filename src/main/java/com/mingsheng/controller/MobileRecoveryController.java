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


    /**
     * 回收首页得列表
     * @param request
     * @param response
     * @return
     */
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


    @ResponseBody
    @RequestMapping(value = "/createOrder")
    public JSONObject insert(HttpServletRequest request,String token,String mobileId,Integer status,String addressId){

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
