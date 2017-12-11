package com.mingsheng.controller;

import com.mingsheng.model.Code;
import com.mingsheng.model.User;
import com.mingsheng.model.UserAddress;
import com.mingsheng.service.CodeService;
import com.mingsheng.service.UserAddressService;
import com.mingsheng.service.UserService;
import com.mingsheng.utils.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private CodeService codeService;


    /**
     * 注册验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/regCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject regCode(String phone) {
        try {
            if(phone ==null|| phone.trim().length()<=0){
                return RespStatus.fail("手机号不能为空");
            }
            User user = userService.getUserByPhone(phone);
            if(user!=null){
                return RespStatus.fail("该用户已注册，请登录");
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


    /**
     * 忘记密码验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/forgetCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject forgetCode(String phone) {
        try {
            if(phone ==null|| phone.trim().length()<=0){
                return RespStatus.fail("手机号不能为空");
            }
            User user = userService.getUserByPhone(phone);
            if(user==null){
                return RespStatus.fail("您还没注册，请注册！");
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

    /**
     * 注册
     * @param phone
     * @param pwd
     * @param code
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject register(String phone, String pwd, String code) {
        try {
            if (phone == null || phone.trim().length() <= 0) {
                return RespStatus.fail("手机号码不能为空！");
            } else {
                if (phone.trim().length() != 11) {
                    return RespStatus.fail("手机号码格式错误！");
                } else {
                    User user = userService.getUserByPhone(phone);
                    if (user != null) {
                        return RespStatus.fail("此手机号码已注册！");
                    }
                }
            }
            if (code == null || code.trim().length() <= 0) {
                return RespStatus.fail("验证码不能为空！");
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
            if (pwd == null || pwd.trim().length() <= 0) {
                return RespStatus.fail("密码不能为空！");
            }
            if (pwd.trim().length() < 6 || pwd.trim().length() > 20) {
                return RespStatus.fail("密码6-20个字符！");
            }
            String uuid = MyUUID.getUUID();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            String phone1 = phone.substring(0, 3);
            String phone2 = phone.substring(7, 11);
            if (pwd == null || pwd == "") {
                pwd = phone.substring(5);
            }
            Integer count = userService.savaUser(uuid, phone, pwd, time, phone1 + "****" + phone2);
            if(count>=1){
                User userByPhone = userService.getUserByPhone(phone);
                Map<String,Object> map = new HashMap<>();
                map.put("nickName",userByPhone.getNickname());
                List<UserAddress> userAddresses = userAddressService.listByUserId(userByPhone.getId());
                if(userAddresses.size()>0){
                    map.put("addressExit",1);
                }else {
                    map.put("addressExit",0);
                }
                return RespStatus.success().element("token", TokenUtil.getToken(uuid)).element("people",map);
            }else {
                return RespStatus.fail("注册失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail();
        }
    }

    @RequestMapping(value = "/forgetPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject forgetPwd(String phone,String password,String code){

        try {
        if (phone == null || phone.trim().length() <= 0) {
            return RespStatus.fail("手机号码不能为空！");
        }else if(phone.trim().length() != 11){
            return RespStatus.fail("手机号码格式错误！");
        }
        if(password==null || password.trim().length()<=0){
            return RespStatus.fail("密码不能为空");
        }
        if (code == null || code.trim().length() <= 0) {
            return RespStatus.fail("验证码不能为空！");
        }

            Code code1 = codeService.getCode(phone);
            if(code1==null){
                return RespStatus.fail("验证码无效");
            }
            if(code.equals(code1.getCode())){
                codeService.delCode(phone);
            }else {
                return RespStatus.fail("验证码无效");
            }
        User user = userService.getUserByPhone(phone);
        if(user==null){
            return RespStatus.fail("您还没注册，请注册！");
        }
        userService.updatePad(phone,password);
            Map<String,Object> map = new HashMap<>();
            map.put("nickName",user.getNickname());
            List<UserAddress> userAddresses = userAddressService.listByUserId(user.getId());
            if(userAddresses.size()>0){
                map.put("addressExit",1);
            }else {
                map.put("addressExit",0);
            }
        return RespStatus.success().element("token",TokenUtil.getToken(user.getId())).element("people",map);
    } catch (Exception e) {
        e.printStackTrace();
        return RespStatus.fail("请重试!");
    }

    }


    /**
     * 密码登录
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject login(String phone,String password){
        try {
        if (phone == null || phone.trim().length() <= 0) {
            return RespStatus.fail("手机号码不能为空！");
        }else if(phone.trim().length() != 11){
            return RespStatus.fail("手机号码格式错误！");
        }
        if(password==null || password.trim().length()<=0){
            return RespStatus.fail("密码不能为空");
        }
        User user = userService.getUserByPhone(phone);
        if(user==null){
            return RespStatus.fail("您还没注册，请注册！");
        }
        if(password.equals(user.getPassword())){
            Map<String,Object> map = new HashMap<>();
            map.put("nickName",user.getNickname());
            List<UserAddress> userAddresses = userAddressService.listByUserId(user.getId());
            if(userAddresses.size()>0){
                map.put("addressExit",1);
            }else {
                map.put("addressExit",0);
            }
            return RespStatus.success().element("token", TokenUtil.getToken(user.getId())).element("people",map);
        }else {
            return RespStatus.fail("密码错误，请重新输入");
        }

        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.exception();
        }
    }

    /**
     * 登录验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/loginCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject loginCode(String phone) {
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

    /**
     * 登录验证码
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "/loginByCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject loginByCode(String phone, String code) {
        try {
            if (phone == null || phone.trim().length() <= 0) {
                return RespStatus.fail("手机号码不能为空！");
            }
            if (phone.trim().length() != 11) {
                return RespStatus.fail("手机号码格式错误！");
            }
            if (code == null || code.trim().length() <= 0) {
                return RespStatus.fail("验证码不能为空！");
            }

            Code code1 = codeService.getCode(phone);
            if(code1==null){
                return RespStatus.fail("验证码无效");
            }
            if(code.equals(code1.getCode())){
                codeService.delCode(phone);
            }else {
                return RespStatus.fail("验证码无效");
            }
              User user = userService.getUserByPhone(phone);
              if (user != null) {
                  Map<String,Object> map = new HashMap<>();
                  map.put("nickName",user.getNickname());
                  List<UserAddress> userAddresses = userAddressService.listByUserId(user.getId());
                  if(userAddresses.size()>0){
                      map.put("addressExit",1);
                  }else {
                      map.put("addressExit",0);
                  }
                 return RespStatus.success().element("token",TokenUtil.getToken(user.getId()));
               }else {
                  String uuid = MyUUID.getUUID();
                  Timestamp time = new Timestamp(System.currentTimeMillis());
                  String phone1 = phone.substring(0, 3);
                  String phone2 = phone.substring(7, 11);
                  String   pwd = phone.substring(5);
                  Integer count = userService.savaUser(uuid, phone, pwd, time, phone1 + "****" + phone2);
                  if(count>=1){
                      Map<String,Object> map = new HashMap<>();
                      map.put("nickName",phone1 + "****" + phone2);
                      map.put("addressExit",0);
                      return RespStatus.success().element("token", TokenUtil.getToken(uuid)).element("people",map);
                  }else {
                      return RespStatus.fail("登陆失败，请重试！");
                  }
              }
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail();
        }
    }


    @RequestMapping(value = "/info",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getUserInfo(String token){
        try {
            if(token==null || token.length()<=0){
                return RespStatus.fail("token不能为空！");
            }
            User user = userService.getUserById(TokenUtil.getId(token));
            if(user==null){
                return RespStatus.fail("无效的token!");
            }
            List<UserAddress> userAddresses = userAddressService.listByUserId(user.getId());
            if(userAddresses.size()<=0){
                Map<String,Object> map = new HashMap<>();
                map.put("nickname",user.getNickname());
                map.put("addressExit",0);
                return RespStatus.success().element("people",map);
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("nickname",user.getNickname());
                map.put("addressExit",1);
                return RespStatus.success().element("people",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespStatus.fail("获取个人信息失败");
        }

    }

}
