package com.mingsheng.controller;

import com.mingsheng.model.*;
import com.mingsheng.service.*;
import com.mingsheng.utils.MyUUID;
import com.mingsheng.utils.RespStatus;
import com.mingsheng.utils.StringUtil;
import com.mingsheng.utils.TokenUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(value = "mobileRepair")
public class MobileRepairController {

    ;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private MobileTypeService mobileTypeService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionOrderService orderService;



    @ResponseBody
    @RequestMapping(value = "getQuestionByType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getList(HttpServletRequest request, HttpServletResponse response,Integer type){
        try {
            if(type==null){
                return RespStatus.fail("查询问题的类型为空");
            }
            List<Question> list = questionService.getListByType(type);
            return RespStatus.success().element("list",list);
        } catch (Exception e) {
            e.printStackTrace();

            return RespStatus.fail("获取列表失败");
        }

    }


    @ResponseBody
    @RequestMapping(value = "getResult", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getQuestionResult(HttpServletRequest request,HttpServletResponse response,String questionId){
        try {
           Question question = questionService.selectResultById(questionId);
            return RespStatus.success().element("data",question);
        } catch (Exception e) {
            e.printStackTrace();

            return RespStatus.fail("获取方案错误");
        }
    }



    @ResponseBody
    @RequestMapping(value = "createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject createOrder(HttpServletRequest request,HttpServletResponse response,String token,String questionId,Integer status,String addressId,String remark,String mobileId){

        try {
        if(token==null || token.length()<=0){
            return RespStatus.fail("token不能为空");
        }
        if(questionId ==null || questionId.length()<=0){
            return RespStatus.fail("问题不能为空");
        }
        if(status==null){
            return RespStatus.fail("请填写回收方式");
        }
        if(mobileId==null || mobileId.length()<=0){
            return RespStatus.fail("手机型号不能为空");
        }
        if(addressId==null || addressId.length()<=0){
            return RespStatus.fail("地址不能为空");
        }
        User user = userService.getUserById(TokenUtil.getId(token));
        if(user ==null){
            return RespStatus.fail("该用户不存在");
        }
        UserAddress userAddress = userAddressService.getAddById(addressId);
        if(userAddress==null){
            return RespStatus.fail("地址不存在");
        }

            QuestionOrder order = new QuestionOrder();
            order.setId(MyUUID.getUUID());
            order.setAddress(userAddress.getAddress());
            order.setCtime(new Timestamp(System.currentTimeMillis()));
            order.setName(userAddress.getName());

            MobileType mobileColour = mobileTypeService.getInfoById(mobileId);
            MobileType mobileMemory = mobileTypeService.getInfoById(mobileColour.getPid());
            MobileType mobileName = mobileTypeService.getInfoById(mobileMemory.getPid());
            MobileType mobileType = mobileTypeService.getInfoById(mobileName.getPid());
            order.setMobileName(mobileName.getName());
            order.setMobileType(mobileType.getName());
            order.setMobileColour(mobileColour.getName());
            order.setMobileMemory(mobileMemory.getName());
            order.setOrderNo(StringUtil.getOrderNum());
            order.setOrderStatus(status);
            order.setPhone(userAddress.getPhone());
            order.setUserId(user.getId());
            order.setQuestionId(questionId);
            Question question = questionService.selectResultById(questionId);
            order.setQuestionResult(question.getQuestionResult());
            if(remark==null || remark.length()<=0){
                order.setRemark("");
            }else {
                order.setRemark(remark);
            }
            orderService.insert(order);

        return null;

        } catch (Exception e) {
            e.printStackTrace();

            return RespStatus.fail("获取方案错误");
        }
    }
}
