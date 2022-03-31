package com.xiaoli.clientservice.service.impl;

import com.xiaoli.clientservice.entity.StoreOrder;
import com.xiaoli.clientservice.mapper.StoreOrderMapper;
import com.xiaoli.clientservice.service.StoreOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@Service
public class StoreOrderServiceImpl extends ServiceImpl<StoreOrderMapper, StoreOrder> implements StoreOrderService {

}
