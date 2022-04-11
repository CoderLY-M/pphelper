package com.xiaoli.clientservice.service.impl;

import com.xiaoli.clientservice.entity.StoreMember;
import com.xiaoli.clientservice.mapper.StoreMemberMapper;
import com.xiaoli.clientservice.service.StoreMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private StoreMemberMapper storeMemberMapper;


    /**
     * 注册用户，返回用户的主键
     * @param storeMember
     * @return 主键
     */
    @Override
    public String registerUser(StoreMember storeMember) {
        storeMemberMapper.insert(storeMember);
        return storeMember.getId();
    }
}
