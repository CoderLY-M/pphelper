package com.xiaoli.basicservice.service.impl;

import com.xiaoli.basicservice.entity.StoreMember;
import com.xiaoli.basicservice.mapper.StoreMemberMapper;
import com.xiaoli.basicservice.service.StoreMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@Service
public class StoreMemberServiceImpl extends ServiceImpl<StoreMemberMapper, StoreMember> implements StoreMemberService {

}
