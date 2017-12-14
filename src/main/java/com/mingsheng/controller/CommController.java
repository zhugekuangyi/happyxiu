package com.mingsheng.controller;

import com.mingsheng.model.Brand;
import com.mingsheng.model.User;
import com.mingsheng.service.BrandService;
import com.mingsheng.service.CodeService;
import com.mingsheng.utils.ImgUtils;
import com.mingsheng.utils.RespStatus;
import com.mingsheng.utils.SmsUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "mine")
public class CommController {
    @Autowired
    private BrandService brandService;

    @Autowired
    private CodeService codeService;

    @RequestMapping(value = "/getMobileList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getListByUserId(String token){
        if(token==null || token.length()<=0){
            return RespStatus.fail("token不能为空");
        }
        return null;
    }



    @RequestMapping(value = "/brandList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getList(HttpServletRequest request, HttpServletResponse response,Integer type){

        List brandList = new ArrayList();
        List<Brand> list = brandService.getList(type);
        for (Brand b:list) {
            if(b.getImg()==null || b.getImg().trim().length()<=0){
                brandList.add(ImgUtils.defaultUrl);
            }else {
                brandList.add(ImgUtils.imgUrl+b.getImg());
            }
        }
        return RespStatus.success().element("brand",brandList);
    }

    /**
     * 订单验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/getCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject regCode(String phone) {
        try {
            if(phone ==null|| phone.trim().length()<=0){
                return RespStatus.fail("手机号不能为空");
            }
            Random r = new Random();
            String code = "";
            for (int i = 1; i <= 6; i++) {
                code += String.valueOf(r.nextInt(10));
            }
            SmsUtils.veriCode(phone, code);
            codeService.addCode(phone,code);
            return RespStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }
    }

}
