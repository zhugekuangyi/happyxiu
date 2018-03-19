package com.mingsheng.controller;

import com.mingsheng.model.*;
import com.mingsheng.service.*;
import com.mingsheng.utils.MyUUID;
import com.mingsheng.utils.RespStatus;
import com.mingsheng.utils.StringUtil;
import com.mingsheng.utils.TokenUtil;
import javafx.beans.binding.ObjectExpression;
import com.mingsheng.utils.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CodeService codeService;
    @Autowired
    private QuestionOrderService questionOrderService;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private RepairResultService repairResultService;


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
    public JSONObject getQuestionResult(HttpServletRequest request,HttpServletResponse response,String questionId,String mobileId){
        try {
           Question question = questionService.selectResultById(questionId);
           if(questionId=="" || questionId==null){
               return RespStatus.fail("维修问题不能为空");
           }
            RepairResult result = repairResultService.getResultById(questionId,mobileId);
            return RespStatus.success().element("data",result);
        } catch (Exception e) {
            e.printStackTrace();

            return RespStatus.fail("获取方案错误");
        }
    }



    @ResponseBody
    @RequestMapping(value = "createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject createOrder(HttpServletRequest request,HttpServletResponse response,String token,String questionId,Integer status,String addressId,String remark,String mobileId,String code,String phone){

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
        if (code == null || code.trim().length() <= 0) {
            return RespStatus.fail("验证码不能为空！");
        }
        if(phone==null || phone.length()<=0){
            return RespStatus.fail("手机号码不能为空");
        }
        Code code1 = codeService.getCode(phone);
        if(code1==null){
            return RespStatus.fail("验证码错误");
        }
        if(code.equals(code1.getCode())){
            codeService.delCode(phone);
        }else {
            return RespStatus.fail("验证码错误");
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
            MobileType mobileName = mobileTypeService.getInfoById(mobileColour.getPid());
            MobileType mobileType = mobileTypeService.getInfoById(mobileName.getPid());
            order.setMobileName(mobileName.getName());
            order.setMobileType(mobileType.getName());
            order.setMobileColour(mobileColour.getName());
            order.setOrderNo(StringUtil.getOrderNum());
            order.setOrderStatus(status);
            order.setPhone(phone);
            order.setUserId(user.getId());
            order.setQuestionId(questionId);
            Question question = questionService.selectResultById(questionId);
            order.setQuestionResult(question.getQuestionResult());
            order.setPrice(question.getPrice());
            if(remark==null || remark.length()<=0){
                order.setRemark("");
            }else {
                order.setRemark(remark);
            }
            orderService.insert(order);

            String phone1 = phoneService.getPhone("3");
            if(phone1==null || phone1 ==""){
                phone1="13685753795";
            }
            SmsUtils.veriOrder(phone1,phone,userAddress.getAddress(),"维修",mobileType.getName()+"/"+mobileName.getName()+"/"+mobileColour.getName());

        return RespStatus.success();

        } catch (Exception e) {
            e.printStackTrace();

            return RespStatus.fail("获取方案错误");
        }
    }


    @RequestMapping(value = "getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getRepairList(@RequestParam(value = "phone") String phone){
        List<Map<String,Object>> list=new ArrayList<>();
        try {
            List<QuestionOrder> orders = questionOrderService.getList(phone);
            for (QuestionOrder order:orders) {
                Map<String,Object> map = new HashMap<>();
                map.put("orderNo",order.getOrderNo());
                map.put("name",order.getName());
                map.put("phone",order.getPhone());
                map.put("mobileType",order.getMobileType());
                map.put("mobileName",order.getMobileName());
                map.put("mobileColour",order.getMobileColour());
                map.put("address",order.getAddress());
                Question question = questionService.selectResultById(order.getQuestionId());
                String s ="";
                if("1".equals(question.getQuestionType())){
                    s="屏幕问题";
                }else if("2".equals(question.getQuestionType())){
                    s="外壳问题";
                }else if("3".equals(question.getQuestionType())){
                    s="电池问题";
                }else if("4".equals(question.getQuestionType())){
                    s="声音问题";
                }else if("5".equals(question.getQuestionType())){
                    s="按键问题";
                }else if("6".equals(question.getQuestionType())){
                    s="摄像拍照";
                }else if("7".equals(question.getQuestionType())){
                    s="内存神经";
                }else {
                    s="其他问题";
                }
                map.put("question",s+"/"+question.getDescription());
                map.put("time",question.getCtime().toString());
                map.put("remark",order.getRemark());
                if(0==order.getOrderStatus()){
                    map.put("orderStatus","到店维修");
                }else if(1==order.getOrderStatus()){
                    map.put("orderStatus","上门维修");
                }else {
                    map.put("orderStatus","快递");
                }

                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespStatus.fail();
        }

        return RespStatus.success().element("list",list);
    }
}
