package com.xiaoli.clientservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoli.clientservice.entity.*;
import com.xiaoli.clientservice.service.*;
import com.xiaoli.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/member")
public class StoreMemberController {
    @Autowired
    private StoreMemberService storeMemberService;
    @Autowired
    private StoreProductImageService storeProductImageService;
    @Autowired
    private StoreProductService storeProductService;
    @Autowired
    private StoreStatusService storeStatusService;
    @Autowired
    private StoreMemberWalletService storeMemberWalletService;

    //用户登录请求
    @PostMapping("/login")
    public Result login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("username", username);
        queryMap.put("password", password);
        StoreMember member = storeMemberService.getOne(new QueryWrapper<StoreMember>().allEq(queryMap));
        if(member != null) {
            return Result.ok().data("member", member);
        }else {
            //获取用户名与密码
            return Result.error();
        }
    }

    //注册用户请求
    @PostMapping("/register")
    public Result registerMember(
            @RequestBody StoreMember registerMember){
        //检查用户名
        StoreMember queryMemberUserName = storeMemberService.getOne(new QueryWrapper<StoreMember>().eq("username", registerMember.getUsername()));
        Integer userNameRep = 202;//用户名重复
        if(queryMemberUserName != null) {
            //用户名重复
            return Result.error().code(userNameRep);
        }
        //检查手机号
        Integer phoneNameRep = 203;//手机号码重复
        StoreMember queryMemberPhone = storeMemberService.getOne(new QueryWrapper<StoreMember>().eq("phone", registerMember.getPhone()));
        if(queryMemberPhone != null) {
            //手机号码重复
            return Result.error().code(phoneNameRep);
        }
        //注册用户
        String masterId= storeMemberService.registerUser(registerMember);
        //查询注册用户
        StoreMember storeMember = storeMemberService.getById(masterId);
        //注册用户钱包
        StoreMemberWallet storeMemberWallet = new StoreMemberWallet();
        storeMemberWallet.setMemberId(storeMember.getId());
        boolean flag = storeMemberWalletService.save(storeMemberWallet);
        if(flag) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //通过用户名获取用户信息请求
    @GetMapping("/memberdata")
    public Result getMemberInfoByUsername(
            @RequestParam("username") String username
    ){
        //获取用户名与密码
        StoreMember useInfo = storeMemberService.getOne(new QueryWrapper().eq(true,"username", username));
        return Result.ok().data("member", useInfo);
    }

    //更新用户数据
    @PostMapping(value = "/updateMember")
    public Result updateMember(@RequestBody StoreMember storeMember) {
        boolean flag = storeMemberService.updateById(storeMember);
        if(flag) {
            StoreMember member = storeMemberService.getById(storeMember.getId());
            return Result.ok().data("member", member);
        }else {
            return Result.error();
        }
    }

    //通过用户id获取用户挂售的商品
    @GetMapping("/getSaleProducts")
    public Result getSaleProducts(
            @RequestParam("mid") String mid){
        //获取用户名与密码
        List<StoreProduct> saleProducts = storeProductService.list(new QueryWrapper<StoreProduct>().eq("member_id", mid));
        if(saleProducts != null) {
            //获取数据成功
            //构造json
            List productList = new ArrayList();
            for (StoreProduct record : saleProducts) {
                Map queryProductMap = new HashMap<>();
                queryProductMap.put("id", record.getId());
                queryProductMap.put("productName", record.getProductName());
                queryProductMap.put("productPrice", record.getProductPrice());
                queryProductMap.put("productDesc", record.getProductDesc());
                queryProductMap.put("productDetail", record.getProductDetail());
                queryProductMap.put("sortId", record.getSortId());
                queryProductMap.put("status", record.getStatus());
                //获取图片地址
                List<StoreProductImage> productImageList = storeProductImageService.list(new QueryWrapper<StoreProductImage>().eq("product_id", record.getId()));
                queryProductMap.put("imageUrl", productImageList.get(0).getImageUrl());
                //获取商品状态（浏览量）
                StoreStatus productStatus = storeStatusService.getOne(new QueryWrapper<StoreStatus>().eq("product_id", record.getId()));
                queryProductMap.put("viewCount", productStatus.getViewCount());
                queryProductMap.put("productCount", productStatus.getProductCount());
                productList.add(queryProductMap);
            }
            return Result.ok().data("lists", productList);
        }else {
            return  Result.error();
        }
    }


    //通过用户id获取用户挂售的商品数量
    @GetMapping("/getSaleCounts")
    public Result getSaleCounts(
            @RequestParam("mid") String mid){
        //获取用户名与密码
        int saleCounts = storeProductService.count(new QueryWrapper<StoreProduct>().eq("member_id", mid));
        return Result.ok().data("counts", saleCounts);
    }

    //通过用户id获取用户信息
    @GetMapping("/getMemberInfo")
    public Result getMemberInfo(
            @RequestParam("mid") String mid){
        //获取用户名与密码
        StoreMember member = storeMemberService.getById(mid);
        if(member != null) {
            return Result.ok().data("member", member);
        }else {
            return Result.error();
        }

    }
}

