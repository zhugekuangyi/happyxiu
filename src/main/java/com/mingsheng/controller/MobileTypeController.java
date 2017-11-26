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


    /**
     * 获取手机类型
      * @return
     */
    @ResponseBody
    @RequestMapping(value = "getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getList(Integer status){

        List<MobileType> list = null;
        List<MobileType> listByPid;
        try {
          if(status ==null){
              return RespStatus.fail("查询状态不能为空");
          }

            list = mobileTypeService.getList(status);
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

    @ResponseBody
    @RequestMapping(value = "getListByMobile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getListByMobile(String mobileId){

        List<MobileType> mobileTypeList = null;
        try {
            if(mobileId==null || mobileId.trim().length()<=0){
                return RespStatus.fail("token不能为空");
            }
           mobileTypeList = mobileTypeService.getListByPid(mobileId);
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }

        return RespStatus.success().element("list",mobileTypeList);


    }
}
