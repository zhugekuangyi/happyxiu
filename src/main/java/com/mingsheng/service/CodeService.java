package com.mingsheng.service;

import com.mingsheng.mapper.CodeMapper;
import com.mingsheng.model.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    @Autowired
    private CodeMapper codeMapper;

    public void addCode(String phone,String code){
        Code codeByphone = codeMapper.getCodeByphone(phone);
        if(codeByphone==null){
            codeMapper.add(phone,code);
        }else {
            codeMapper.update(phone,code);
        }
    }

    public void delCode(String phone){
        codeMapper.del(phone);
    }

    public Code getCode(String phone){
        Code codeByphone = codeMapper.getCodeByphone(phone);
        return codeByphone;
    }
}
