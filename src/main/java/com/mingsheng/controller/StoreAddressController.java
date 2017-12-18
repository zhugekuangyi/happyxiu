package com.mingsheng.controller;

import com.mingsheng.model.StoreAddress;
import com.mingsheng.service.StoreAddressService;
import com.mingsheng.utils.ImgUtils;
import com.mingsheng.utils.MyUUID;
import com.mingsheng.utils.OSSUtil;
import com.mingsheng.utils.RespStatus;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping(value = "store")
public class StoreAddressController {
    @Autowired
    private StoreAddressService storeAddressService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject list(HttpServletRequest request, HttpServletResponse response){

        try {
            List<StoreAddress> storeAddress = storeAddressService.getStoreAddress();
            return RespStatus.success().element("store",storeAddress);
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail("获取失败!");
        }

    }
    @ResponseBody
    @RequestMapping(value = "/addStore", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject addStore(@RequestParam(value = "title") String title, @RequestParam(value = "address")String address, @RequestParam(value = "remark")String remark, @RequestParam(value = "img")String img){
        try {
            if(img==null || img.trim().length()<0){
                return RespStatus.fail("图片不能为空");
            }
            if(address==null || address.trim().length()<=0){
                return RespStatus.fail("地址不能空");
            }
            if(title==null || title.trim().length()<=0){
                return RespStatus.fail("标题不能空");
            }
            if(remark==null || remark.trim().length()<=0){
                return RespStatus.fail("服务时间不能空");
            }
            StoreAddress store = new StoreAddress();
            Random r = new Random();
            String code = "";
            for (int i = 1; i <= 6; i++) {
                code += String.valueOf(r.nextInt(10));
            }
            store.setId(code);
            store.setAddress(address);
            store.setCtime(new Timestamp(System.currentTimeMillis()));
            store.setRemark(remark);
            store.setTitle(title);
            Map<String, String> map = OSSUtil.baseToFile(img);
            if("0".equals(map.get("code"))){
                store.setImg(ImgUtils.imgUrl+map.get("fileName"));
            }else {
                store.setImg(ImgUtils.defaultUrl);
            }
            storeAddressService.insert(store);
        return RespStatus.success();
        }catch (Exception e){
            e.printStackTrace();
            return RespStatus.fail("保存失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONObject delStore(@RequestParam(value = "id") String id){
        try {
            storeAddressService.del(id);
            return RespStatus.success();
        }catch (Exception e){
            e.printStackTrace();
            return RespStatus.fail("保存失败");
        }
    }

}
