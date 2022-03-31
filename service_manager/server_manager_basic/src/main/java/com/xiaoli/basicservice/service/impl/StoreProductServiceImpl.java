package com.xiaoli.basicservice.service.impl;

import com.xiaoli.basicservice.entity.StoreProduct;
import com.xiaoli.basicservice.mapper.StoreProductMapper;
import com.xiaoli.basicservice.service.StoreProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@Service
public class StoreProductServiceImpl extends ServiceImpl<StoreProductMapper, StoreProduct> implements StoreProductService {

}
