package com.mingsheng.controller;

import com.mingsheng.model.StoreAddress;
import com.mingsheng.service.StoreAddressService;
import com.mingsheng.utils.RespStatus;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "store")
public class StoreAddressController {
    @Autowired
    private StoreAddressService storeAddressService;

    public JSONObject list(HttpServletRequest request, HttpServletResponse response){

        try {
            List<StoreAddress> storeAddress = storeAddressService.getStoreAddress();
            return RespStatus.success().element("store",storeAddress);
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail("获取失败!");
        }

    }
}
