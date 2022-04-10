package com.xiaoli.clientservice.service.impl;

import com.xiaoli.clientservice.entity.StoreChartMaster;
import com.xiaoli.clientservice.mapper.StoreChartMasterMapper;
import com.xiaoli.clientservice.service.StoreChartMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天主表 服务实现类
 * </p>
 *
 * @author xiaoli
 * @since 2022-04-09
 */
@Service
public class StoreChartMasterServiceImpl extends ServiceImpl<StoreChartMasterMapper, StoreChartMaster> implements StoreChartMasterService {
    @Autowired
    private StoreChartMasterMapper storeChartMasterMapper;

    @Override
    public String connectCreate(StoreChartMaster connection) {
        storeChartMasterMapper.insert(connection);
        return connection.getId();
    }
}
