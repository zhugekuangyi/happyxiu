package com.mingsheng.controller;

import com.mingsheng.model.MobileType;
import com.mingsheng.service.MobileTypeService;
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
@RequestMapping(value = "mobile")
public class MobileTypeController {

    @Autowired
    private MobileTypeService mobileTypeService;


    /**
     * 获取手机类型
      * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getList(Integer status){

        List<MobileType> list = null;
        List<MobileType> listByPid;
        List<Map<String,Object>> mapList = new ArrayList<>();
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

            for (MobileType mt:list) {
                Map<String,Object> map = new HashMap<>();
                map.put("name",mt.getName());
                map.put("time",mt.getCtime().toString());
                map.put("id",mt.getId());
                mapList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }

        return RespStatus.success().element("list",mapList).element("listDetail",listByPid);


    }

    @ResponseBody
    @RequestMapping(value = "saveMobileType",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject saveMobileType(@RequestParam(value = "mobileType") String mobileType,@RequestParam(value = "status") Integer status){
        try {


        if(mobileType==null || mobileType==""){
            return RespStatus.fail("手机品牌不能为空");
        }
        mobileTypeService.saveMobile(status,mobileType,1,"");
        }catch (Exception e){
            return RespStatus.fail("添加失败！");
        }
        return RespStatus.success();
    }

    /**
     * 获取手机具体型号
     * @param mobileId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getListByMobile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getListByMobile(String mobileId){

        List<MobileType> mobileTypeList = null;
        try {
            if(mobileId==null || mobileId.trim().length()<=0){
                return RespStatus.fail("手机厂商不能为空");
            }
            mobileTypeList = mobileTypeService.getListByPid(mobileId);
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }

        return RespStatus.success().element("list",mobileTypeList);


    }


    /**
     * 获取手机具体型号
     * @param mobileId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMobileName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getMobileName(String mobileId){

        List<MobileType> mobileTypeList = null;
        List<Map<String,Object>> mapList = new ArrayList<>();
        try {
            if(mobileId==null || mobileId.trim().length()<=0){
                return RespStatus.fail("手机厂商不能为空");
            }
            mobileTypeList = mobileTypeService.getListByPid(mobileId);

            for (MobileType mt:mobileTypeList) {
                Map<String,Object> map = new HashMap<>();
                map.put("mobileType","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }

        return RespStatus.success().element("list",mobileTypeList);


    }


    /**
     * 获取手机具体型号
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getColourListByMobile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getColourListByMobile(String Id){

        List<MobileType> mobileTypeList = null;
        try {
            if(Id==null || Id.trim().length()<=0){
                return RespStatus.fail("token不能为空");
            }
            mobileTypeList = mobileTypeService.getListByPid(Id);
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }

        return RespStatus.success().element("list",mobileTypeList);


    }


    /**
     * 获取手机内存
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMemoryListByMobile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getMemoryListByMobile(String Id){

        List<MobileType> mobileTypeList = null;
        try {
            if(Id==null || Id.trim().length()<=0){
                return RespStatus.fail("token不能为空");
            }
            mobileTypeList = mobileTypeService.getListByPid(Id);
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }

        return RespStatus.success().element("list",mobileTypeList);


    }


    @ResponseBody
    @RequestMapping(value = "getMobileType", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONObject getList(){
        List<MobileType> list = mobileTypeService.getMobileList();
        List<Map<String,Object>> mapList = new ArrayList<>();
         for (MobileType mt:list) {
            Map<String,Object> map = new HashMap<>();
            map.put("name",mt.getName());
            map.put("id",mt.getId());
            if(mt.getStatus()==0){
                map.put("status","维修");
            }else {
                map.put("status","回收");
            }
            mapList.add(map);
        }
        return RespStatus.success().element("list",mapList);
    }

    @RequestMapping(value = "del",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject del(String id){

        try {
            if(id==null){
                return RespStatus.fail("传入的ID不能为空");
            }
            mobileTypeService.del(id);
        }catch (Exception e){
            return RespStatus.fail("删除失败");
        }
        return RespStatus.success();
    }
}
