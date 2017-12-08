package com.mingsheng.service;

import com.mingsheng.mapper.SysUserMapper;
import com.mingsheng.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    public boolean login(String name,String pwd){
        SysUser sysUser = sysUserMapper.selectByName(name);
        if (sysUser == null ||!pwd.equals(sysUser.getPwd())) {
            return false;
        }else {
            return true;
        }
    }
}
