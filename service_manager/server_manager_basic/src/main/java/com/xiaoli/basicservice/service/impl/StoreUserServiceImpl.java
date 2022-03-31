package com.xiaoli.basicservice.service.impl;

import com.xiaoli.basicservice.entity.StoreUser;
import com.xiaoli.basicservice.mapper.StoreUserMapper;
import com.xiaoli.basicservice.service.StoreUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@Service
public class StoreUserServiceImpl extends ServiceImpl<StoreUserMapper, StoreUser> implements StoreUserService {

}
