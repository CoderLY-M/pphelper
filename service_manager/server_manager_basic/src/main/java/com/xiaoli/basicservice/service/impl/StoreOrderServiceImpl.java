package com.xiaoli.basicservice.service.impl;

import com.xiaoli.basicservice.entity.StoreOrder;
import com.xiaoli.basicservice.mapper.StoreOrderMapper;
import com.xiaoli.basicservice.service.StoreOrderService;
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
