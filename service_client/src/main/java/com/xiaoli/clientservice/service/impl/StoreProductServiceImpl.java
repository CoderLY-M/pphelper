package com.xiaoli.clientservice.service.impl;

import com.xiaoli.clientservice.entity.StoreProduct;
import com.xiaoli.clientservice.mapper.StoreProductMapper;
import com.xiaoli.clientservice.service.StoreProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private  StoreProductMapper storeProductMapper;

    @Override
    public String saveProductData(StoreProduct storeProduct) {
        //插入数据
        storeProductMapper.insert(storeProduct);
        //放回id主键值
        return storeProduct.getId();
    }
}
