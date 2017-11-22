package com.mingsheng.mapper;

import com.mingsheng.model.MobileRecovery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MobileRecoveryMapper {

        List<MobileRecovery> getList();
}
