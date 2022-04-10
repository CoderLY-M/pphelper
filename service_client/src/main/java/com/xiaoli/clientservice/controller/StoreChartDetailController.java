package com.xiaoli.clientservice.controller;


import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 聊天详情表 前端控制器
 * </p>
 *
 * @author xiaoli
 * @since 2022-04-09
 */
@RestController
@RequestMapping("/chartDetail")
public class StoreChartDetailController {
    @Autowired
    private StoreChartMasterService storeChartMasterService;
    @Autowired
    private StoreChartListService storeChartListService;
    @Autowired
    private StoreMemberService storeMemberService;
    @Autowired
    private StoreChartDetailService storeChartDetailService;

    //查询当前用户与指定用户的消息列表
    @GetMapping("/messageList")
    public Result getMemberList(
            @RequestParam("memberId") String memberId,
            @RequestParam("anotherId") String anotherId
    ){
        List messageList = new ArrayList();
        if(memberId != "" && anotherId != "") {
            //判断双方是否已经建立连接
            HashMap queryMap = new HashMap<>();
            queryMap.put("member_id", memberId);
            queryMap.put("another_id", anotherId);
            StoreChartMaster existConnect = storeChartMasterService.getOne(new QueryWrapper<StoreChartMaster>().allEq(queryMap));
            HashMap queryMap1 = new HashMap<>();
            queryMap1.put("member_id", anotherId);
            queryMap1.put("another_id", memberId);
            StoreChartMaster existConnect1 = storeChartMasterService.getOne(new QueryWrapper<StoreChartMaster>().allEq(queryMap1));
            if(existConnect != null || existConnect1 != null) {
                //已经建立过连接
                //获取消息历史记录
                //1.获取主表主键
                String masterId = "";
                if(existConnect != null) {
                    masterId = existConnect.getId();
                }
                if(existConnect1 != null) {
                    masterId = existConnect1.getId();
                }
                //2.获取(自己的）聊天记录
                Map queryForMapOwner = new HashMap();
                queryForMapOwner.put("chart_id", masterId);
                queryForMapOwner.put("member_id", memberId);
                List<StoreChartDetail> mySelfMessages = storeChartDetailService.list(new QueryWrapper<StoreChartDetail>().allEq(queryForMapOwner));
                if(mySelfMessages != null && mySelfMessages.size() != 0) {
                    //存在聊天记录
                    for (StoreChartDetail mySelfMessage : mySelfMessages) {
                        HashMap messageMap = new HashMap();
                        messageMap.put("owner", 1);
                        messageMap.put("message", mySelfMessage.getContent());
                        messageMap.put("time", mySelfMessage.getTime());
                        messageList.add(messageMap);
                    }
                }
                //3.获取(对方的）聊天记录
                Map queryForMapOther = new HashMap();
                queryForMapOther.put("chart_id",masterId);
                queryForMapOther.put("member_id", anotherId);
                List<StoreChartDetail> otherMessages = storeChartDetailService.list(new QueryWrapper<StoreChartDetail>().allEq(queryForMapOther));
                if(otherMessages != null && otherMessages.size() != 0) {
                    for (StoreChartDetail otherMessage : otherMessages) {
                        HashMap messageMap = new HashMap();
                        messageMap.put("owner", 0);
                        messageMap.put("message", otherMessage.getContent());
                        messageMap.put("time", otherMessage.getTime());
                        messageList.add(messageMap);
                    }
                }
                messageList.sort(new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        HashMap obj1 = (HashMap) o1;
                        HashMap obj2 = (HashMap) o2;
                        Date time1 = (Date) obj1.get("time");
                        Date time2 = (Date) obj2.get("time");
                        return (int) (time1.getTime() - time2.getTime());
                    }
                });
                return  Result.ok().data("lists", messageList);
            }else {
                return Result.error();
            }
        }else {
            return Result.error();
        }
    }

    //发送消息
    @PostMapping("/sendMessage")
    public Result sendMessage(@RequestBody JSONObject jsonObject){
        //解析json
        String memberId = jsonObject.getString("memberId").trim();
        String anotherId = jsonObject.getString("anotherId").trim();
        String message = jsonObject.getString("message").trim();
        if(memberId != "" && anotherId != "") {
            //查询数据库中是否有该用户
            StoreMember member = storeMemberService.getById(memberId);
            StoreMember another = storeMemberService.getById(anotherId);
            if(member == null || another == null) {
                return Result.error();
            }else {
                //判断双方是否已经建立连接
                HashMap queryMap = new HashMap<>();
                queryMap.put("member_id", memberId);
                queryMap.put("another_id", anotherId);
                StoreChartMaster existConnect = storeChartMasterService.getOne(new QueryWrapper<StoreChartMaster>().allEq(queryMap));
                HashMap queryMap1 = new HashMap<>();
                queryMap1.put("member_id", anotherId);
                queryMap1.put("another_id", memberId);
                StoreChartMaster existConnectOther = storeChartMasterService.getOne(new QueryWrapper<StoreChartMaster>().allEq(queryMap1));
                if (existConnect == null && existConnectOther == null) {
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
                    storeChartListService.save(chartList);
                    storeChartListService.save(chartListOther);
                }
                //添加消息
                //1.获取主表主键
                existConnect = storeChartMasterService.getOne(new QueryWrapper<StoreChartMaster>().allEq(queryMap));
                existConnectOther = storeChartMasterService.getOne(new QueryWrapper<StoreChartMaster>().allEq(queryMap1));
                String masterId = "";
                if(existConnect != null) {
                    masterId = existConnect.getId();
                }
                if(existConnectOther != null) {
                    masterId = existConnectOther.getId();
                }
                StoreChartDetail messageModel = new StoreChartDetail();
                messageModel.setChartId(masterId);
                messageModel.setMemberId(memberId);
                messageModel.setContent(message);
                messageModel.setType(0);    //目前只支持发送图片
                messageModel.setIsLatest(1);   //最后一条消息
                //查询数据库中消息为最后一条的数据
                HashMap map = new HashMap();
                map.put("member_id", memberId);
                map.put("is_latest", 1);
                StoreChartDetail updateMessage = storeChartDetailService.getOne(new QueryWrapper<StoreChartDetail>().allEq(map));
                if(updateMessage != null) {
                    //存在数据
                    updateMessage.setIsLatest(0);
                    //更新
                    storeChartDetailService.updateById(updateMessage);
                }
                //保存到数据库
                boolean save = storeChartDetailService.save(messageModel);
                if(save) {
                    //发送成功
                    return Result.ok();
                }else {
                    //发送失败
                    return Result.error();
                }
            }
        }else {
            return Result.error();
        }
    }
}

