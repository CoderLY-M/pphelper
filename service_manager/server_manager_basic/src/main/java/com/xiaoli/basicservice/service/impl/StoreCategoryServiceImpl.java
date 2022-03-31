package com.xiaoli.basicservice.service.impl;

import com.xiaoli.basicservice.entity.StoreCategory;
import com.xiaoli.basicservice.mapper.StoreCategoryMapper;
import com.xiaoli.basicservice.service.StoreCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@Service
public class StoreCategoryServiceImpl extends ServiceImpl<StoreCategoryMapper, StoreCategory> implements StoreCategoryService {

}
