package com.mingsheng.controller;

import com.mingsheng.model.MobileType;
import com.mingsheng.service.MobileTypeService;
import com.mingsheng.utils.RespStatus;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "mobile")
public class MobileTypeController {

    @Autowired
    private MobileTypeService mobileTypeService;


    @ResponseBody
    @RequestMapping(value = "getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getList(String token){

        List<MobileType> list = null;
        List<MobileType> listByPid;
        try {
            if(token==null || token.trim().length()<=0){
                return RespStatus.fail("token不能为空");
            }

            list = mobileTypeService.getList();
            if(list.size()>0){
                listByPid = mobileTypeService.getListByPid(list.get(0).getId());

            }else {
                listByPid=new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }

        return RespStatus.success().element("list",list).element("listDetail",listByPid);


    }
}
