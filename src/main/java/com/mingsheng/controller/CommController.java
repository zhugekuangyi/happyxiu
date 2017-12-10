package com.mingsheng.controller;

import com.mingsheng.model.Brand;
import com.mingsheng.service.BrandService;
import com.mingsheng.utils.ImgUtils;
import com.mingsheng.utils.RespStatus;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "mine")
public class CommController {
    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "/getMobileList")
    @ResponseBody
    public JSONObject getListByUserId(String token){
        if(token==null || token.length()<=0){
            return RespStatus.fail("token不能为空");
        }
        return null;
    }



    @RequestMapping(value = "/brandList")
    @ResponseBody
    public JSONObject getList(HttpServletRequest request, HttpServletResponse response){

        List brandList = new ArrayList();
        List<Brand> list = brandService.getList();
        for (Brand b:list) {
            if(b.getImg()!=null){
                brandList.add(ImgUtils.imgUrl+b.getImg());
            }else {
                brandList.add(ImgUtils.defaultUrl);
            }
        }
        return RespStatus.success().element("brand",brandList);
    }

}
