package com.xiaoli.clientservice.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoli.clientservice.entity.*;
import com.xiaoli.clientservice.service.*;
import com.xiaoli.clientservice.utils.FileStorageUtil;
import com.xiaoli.commonutils.Result;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户钱包表 前端控制器
 * </p>
 *
 * @author xiaoli
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/memberWallet")
public class StoreMemberWalletController {
    @Autowired
    private StoreMemberWalletService storeMemberWalletService;
    @Autowired
    private StoreMemberService storeMemberService;
    @Autowired
    private StoreProductService storeProductService;
    @Autowired
    private StoreStatusService storeStatusService;
    @Autowired
    private StoreOrderService storeOrderService;
    @Autowired
    private StorePayLogService storePayLogService;
    @Autowired
    private StoreProductImageService storeProductImageService;

    //用户账号充值
    @PostMapping("/recharge")
    public Result updateMemberWallet(@RequestBody JSONObject jsonObject) {
        String memberId = jsonObject.getString("memberId").trim();
        String tempMoney = jsonObject.getString("money").trim();
        try {
            Double.parseDouble(tempMoney);
        }catch (Exception e) {
            return Result.error();
        }
        //查询数据库中是否有该用户
        StoreMemberWallet storeWalletMember = storeMemberWalletService.getOne(new QueryWrapper<StoreMemberWallet>().eq("member_id", memberId));
        if(storeWalletMember == null) {
            //没有该用户，拒绝
            return Result.error();
        }
        BigDecimal wallet = storeWalletMember.getWallet();
        double addMoney = Double.parseDouble(tempMoney);
        BigDecimal countMoney = wallet.add(BigDecimal.valueOf(addMoney));
        storeWalletMember.setWallet(countMoney);
        //更新余额
        boolean flag = storeMemberWalletService.updateById(storeWalletMember);
        if(flag) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //获取指定用户的钱包余额
    @PostMapping("/account")
    public Result getMemberWalletCount(@RequestBody JSONObject jsonObject) {
        String memberId = jsonObject.getString("memberId").trim();
        //查询数据库中是否有该用户
        StoreMemberWallet storeWalletMember = storeMemberWalletService.getOne(new QueryWrapper<StoreMemberWallet>().eq("member_id", memberId));
        if(storeWalletMember == null) {
            //没有该用户，拒绝
            return Result.error();
        }
        //获取用户余额
        return Result.ok().data("wallet", storeWalletMember.getWallet());
    }

    //购买商品
    @PostMapping("/buyProducts")
    public Result buyProducts(@RequestBody JSONObject jsonObject) {
        String memberId = jsonObject.getString("memberId").trim();
        //获取数据列表
        JSONArray lists = jsonObject.getJSONArray("lists");
        //获取是否有用户
        StoreMember member = storeMemberService.getById(memberId);
        if(member == null || lists == null) {
            //不存在
            return Result.error();
        }
        StoreMemberWallet storeWalletMember = storeMemberWalletService.getOne(new QueryWrapper<StoreMemberWallet>().eq("member_id", memberId));
        //计算商品价格
        for (Object list : lists) {
            HashMap productMap = JSONObject.parseObject(JSONObject.toJSONString(list), HashMap.class);
            //获取用户名
            String productId = (String) productMap.get("productId");
            int willBuyCount = (Integer) productMap.get("productCount");
            StoreProduct product = storeProductService.getById(productId);
            StoreMember seller = storeMemberService.getOne(new QueryWrapper<StoreMember>().eq("id", product.getMemberId()));
            StoreStatus productStatus = storeStatusService.getOne(new QueryWrapper<StoreStatus>().eq("product_id", productId));
            StoreProductImage productImage = storeProductImageService.getOne(new QueryWrapper<StoreProductImage>().eq("product_id", productId));
            if(product == null || seller == null || productStatus == null || productImage == null) {
                return Result.error();
            }
            //获取当前的数量
            if(productStatus.getProductCount() < willBuyCount) {
                //数量不够
                return Result.error();
            }
            //1.生成订单
            StoreOrder storeOrder = new StoreOrder();
            storeOrder.setMemberId(memberId);
            storeOrder.setProductId(product.getId());
            storeOrder.setProductTitle(product.getProductName());
            storeOrder.setNickName(member.getNickName());
            storeOrder.setProductCover(productImage.getImageUrl());
            storeOrder.setSellerId(product.getMemberId());
            storeOrder.setSellerName(seller.getNickName());
            storeOrder.setPhone(seller.getPhone());
            storeOrder.setProductCount((long) willBuyCount);
            storeOrder.setTotalFee(product.getProductPrice().multiply(BigDecimal.valueOf(willBuyCount)));
            storeOrder.setStatus(1);
            storeOrder.setPayType(3);
            //2.保存订单
            storeOrderService.save(storeOrder);
            //3.更新商品状态
            Long buyCount = productStatus.getBuyCount();    //购买人数
            Long productCount = productStatus.getProductCount();
            productStatus.setProductCount(productCount - willBuyCount);
            productStatus.setBuyCount(buyCount + willBuyCount);
            storeStatusService.updateById(productStatus);
            //4.扣取钱包中的money
            BigDecimal wallet = storeWalletMember.getWallet();
            wallet = wallet.subtract(product.getProductPrice().multiply(BigDecimal.valueOf(willBuyCount)));
            //更新钱包
            storeWalletMember.setWallet(wallet);
            storeMemberWalletService.updateById(storeWalletMember);
        }
        return Result.ok();
    }
}

