package com.xiaoli.clientservice.service;

import com.xiaoli.clientservice.entity.StoreChartMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天主表 服务类
 * </p>
 *
 * @author xiaoli
 * @since 2022-04-09
 */
@Service
public interface StoreChartMasterService extends IService<StoreChartMaster> {
    /**
     * 建立连接并返回主键id
     */
    String connectCreate(StoreChartMaster connection);
}
