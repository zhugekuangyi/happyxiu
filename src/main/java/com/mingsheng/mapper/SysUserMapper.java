package com.mingsheng.mapper;

import com.mingsheng.model.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface SysUserMapper {
        SysUser selectByName(@Param(value = "name") String name);
}
