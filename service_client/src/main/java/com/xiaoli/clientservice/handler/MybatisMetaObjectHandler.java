package com.xiaoli.clientservice.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author xiaoli
 * @Date 2022/3/25 17:02
 * @Version 1.0
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //在插入时插入时间
        this.setFieldValByName("gmtCreate", new Date(),metaObject);
        this.setFieldValByName("gmtModified", new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //在更新时更新时间
        this.setFieldValByName("gmtModified", new Date(),metaObject);
    }
}
