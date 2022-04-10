package com.xiaoli.clientservice.service;

import com.xiaoli.clientservice.entity.StoreProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@Service
public interface StoreProductService extends IService<StoreProduct> {
    //保存商品数据
    public String saveProductData(StoreProduct storeProduct);
}
