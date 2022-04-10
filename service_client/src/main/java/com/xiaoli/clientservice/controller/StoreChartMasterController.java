package com.xiaoli.clientservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoli.clientservice.entity.StoreChartDetail;
import com.xiaoli.clientservice.entity.StoreChartList;
import com.xiaoli.clientservice.entity.StoreChartMaster;
import com.xiaoli.clientservice.entity.StoreMember;
import com.xiaoli.clientservice.service.StoreChartDetailService;
import com.xiaoli.clientservice.service.StoreChartListService;
import com.xiaoli.clientservice.service.StoreChartMasterService;
import com.xiaoli.clientservice.service.StoreMemberService;
import com.xiaoli.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 聊天主表 前端控制器
 * </p>
 *
 * @author xiaoli
 * @since 2022-04-09
 */
@RestController
@RequestMapping("/chartMaster")
public class StoreChartMasterController {
    @Autowired
    private StoreChartMasterService storeChartMasterService;
    @Autowired
    private StoreChartListService storeChartListService;
    @Autowired
    private StoreMemberService storeMemberService;
    @Autowired
    private StoreChartDetailService storeChartDetailService;

    //聊天双方建立连接
    @GetMapping("/chartConnectCreate")
    public Result getMemberInfoByUsername(
            @RequestParam("memberId") String memberId,
            @RequestParam("anotherId") String anotherId
    ){
        if(memberId != "" && anotherId != "") {
            //判断双方是否已经建立连接
            HashMap queryMap = new HashMap<>();
            queryMap.put("member_id", memberId);
            queryMap.put("another_id", anotherId);

            StoreChartMaster existConnect = storeChartMasterService.getOne(new QueryWrapper<StoreChartMaster>().allEq(queryMap));
            if(existConnect != null) {
                //已经建立过连接
                return Result.ok();
            }else {
                //没建立过连接
                StoreChartMaster newConnect = new StoreChartMaster();
                newConnect.setMemberId(memberId);
                newConnect.setAnotherId(anotherId);
                String masterId = storeChartMasterService.connectCreate(newConnect);
                //(己方）在聊天记录中插入数据
                StoreChartList chartList = new StoreChartList();
                chartList.setChartId(masterId);
                chartList.setMemberId(memberId);
                chartList.setAnotherId(anotherId);

                //(对方）在聊天记录中插入数据
                StoreChartList chartListOther = new StoreChartList();
                chartListOther.setChartId(masterId);
                chartListOther.setMemberId(anotherId);
                chartListOther.setAnotherId(memberId);

                boolean isCreateChartList = storeChartListService.save(chartList);
                boolean isCreateChartListOther = storeChartListService.save(chartListOther);
                //判断是否已经添加好了用户列表
                if(isCreateChartList && isCreateChartListOther) {
                    return Result.ok();
                }else {
                    return Result.error();
                }
            }
        }else {
            return Result.error();
        }
    }

    //查询当前的消息列表
    @GetMapping("/memberList")
    public Result getMemberList(
            @RequestParam("memberId") String memberId
    ){
        List<StoreChartList> queryChartMemberList = storeChartListService.list(new QueryWrapper<StoreChartList>().eq("member_id", memberId));
        //构造json
        ArrayList<Object> chartMemberList = new ArrayList<>();
        for (StoreChartList storeChartList : queryChartMemberList) {
            String anotherId = storeChartList.getAnotherId();
            StoreMember anotherInfo = storeMemberService.getById(anotherId);
            //查询用户信息
            Map tempChart = new HashMap<>();
            tempChart.put("anotherId", anotherInfo.getId());
            tempChart.put("nickName", anotherInfo.getNickName());
            tempChart.put("avatar", anotherInfo.getAvatar());

            //获取聊天内容列表信息
            chartMemberList.add(tempChart);

            //获取对方id
            String otherId = storeChartList.getAnotherId();
            //通过对方id与自己id确定数据
            HashMap queryMap = new HashMap<>();
            queryMap.put("member_id",memberId);
            queryMap.put("another_id",otherId);
            StoreChartMaster master = storeChartMasterService.getOne(new QueryWrapper<StoreChartMaster>().allEq(queryMap));
            //通过master主表与消息所有者确定消息
            HashMap map = new HashMap<>();
            map.put("member_id", memberId);
            map.put("chart_id", master.getId());
            map.put("is_latest","1");
            StoreChartDetail lastMessage = storeChartDetailService.getOne(new QueryWrapper<StoreChartDetail>().allEq(map));
            if(lastMessage != null) {
                //获取最后一条消息
                tempChart.put("message", lastMessage.getContent());
                tempChart.put("time", lastMessage.getTime().toLocaleString());
            }else {
                //获取最后一条消息
                tempChart.put("message", "");
                tempChart.put("time", "");
            }
        }
        return Result.ok().data("lists", chartMemberList);
    }
}

