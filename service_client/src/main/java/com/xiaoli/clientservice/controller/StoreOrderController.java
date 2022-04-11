package com.xiaoli.clientservice.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoli.clientservice.entity.StoreMember;
import com.xiaoli.clientservice.service.StoreMemberService;
import com.xiaoli.clientservice.service.StoreOrderService;
import com.xiaoli.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/member")
public class StoreOrderController {
    @Autowired
    private StoreMemberService storeMemberService;
    @Autowired
    private StoreOrderService storeOrderService;

    //获取订单列表数据
    @PostMapping("/orderList")
    public Result getOrderList(@RequestBody JSONObject jsonObject){
        //解析json
        String memberId = jsonObject.getString("memberId");
        Integer status = jsonObject.getInteger("status");
        if(memberId == null) {
            return Result.error();
        }
        //获取数据
        HashMap queryMap = new HashMap<>();
        queryMap.put("member_id", memberId);
        if(status != 0) {
            //状态查询
            queryMap.put("status", status);
        }
        List orderList = storeOrderService.list(new QueryWrapper<>().allEq(queryMap));
        if(orderList == null) {
            return Result.error();
        }
        return Result.ok().data("lists", orderList);
    }
}

