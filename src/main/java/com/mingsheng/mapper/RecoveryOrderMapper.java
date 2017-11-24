package com.mingsheng.mapper;

import com.mingsheng.model.RecoveryOrder;
import org.springframework.stereotype.Component;

@Component
public interface RecoveryOrderMapper {

    void addOrder(RecoveryOrder order);
}
