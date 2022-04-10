package com.xiaoli.clientservice.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoli.clientservice.entity.StoreProduct;
import com.xiaoli.clientservice.entity.StoreProductImage;
import com.xiaoli.clientservice.entity.StoreStatus;
import com.xiaoli.clientservice.service.StoreProductImageService;
import com.xiaoli.clientservice.service.StoreProductService;
import com.xiaoli.clientservice.service.StoreStatusService;
import com.xiaoli.clientservice.utils.FileStorageUtil;
import com.xiaoli.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/product")
public class StoreProductController {
    @Autowired
    private StoreProductService storeProductService;
    @Autowired
    private StoreStatusService storeStatusService;
    @Autowired
    private StoreProductImageService storeProductImageService;

    //获取推荐商品
    @GetMapping("/recommendProducts")
    public Result getCategories(){
        List<Map> list = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("id","0");
        map1.put("imageUrl","https://w.wallhaven.cc/full/m9/wallhaven-m9mzr8.jpg");
        map1.put("productName","苹果11");
        map1.put("productPrice",5600.00);
        map1.put("viewCount",565);
        list.add(map1);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("id","1");
        map2.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map2.put("productName","苹果12");
        map2.put("productPrice",5300.00);
        map2.put("viewCount",565);
        list.add(map2);
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("id","2");
        map3.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map3.put("productName","苹果13");
        map3.put("productPrice",9900.00);
        map3.put("viewCount",454545);
        list.add(map3);
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("id","3");
        map4.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map4.put("productName","苹果11");
        map4.put("productPrice",5600.00);
        map4.put("viewCount",4444);
        list.add(map4);
        HashMap<String, Object> map5= new HashMap<>();
        map5.put("id","4");
        map5.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map5.put("productName","VIVO FindX");
        map5.put("productPrice",5600.00);
        map5.put("viewCount",23);
        list.add(map5);
        return Result.ok().data("lists",list);
    }

    //获取在售商品
    @GetMapping("/sellProducts")
    public Result getSellProducts(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        //设置分页
        Page<StoreProduct> queryPage = new Page<>(pageNum,pageSize);
        IPage<StoreProduct> productIPage = storeProductService.page(queryPage, new QueryWrapper<StoreProduct>().eq("status", "1"));
        List<StoreProduct> records = productIPage.getRecords();
        //构建返回对象
        List productList = new ArrayList();
        for (StoreProduct record : records) {
            Map queryProductMap = new HashMap<>();
            queryProductMap.put("id", record.getId());
            queryProductMap.put("productName", record.getProductName());
            queryProductMap.put("productPrice", record.getProductPrice());
            //获取图片地址
            List<StoreProductImage> productImageList = storeProductImageService.list(new QueryWrapper<StoreProductImage>().eq("product_id", record.getId()));
            queryProductMap.put("imageUrl", productImageList.get(0).getImageUrl());
            //获取商品状态（浏览量）
            StoreStatus productStatus = storeStatusService.getOne(new QueryWrapper<StoreStatus>().eq("product_id", record.getId()));
            queryProductMap.put("viewCount", productStatus.getViewCount());
            productList.add(queryProductMap);
        }
        return Result.ok().data("lists",productList);
    }

    //通过商品类别获取商品的数据列表
    @GetMapping("/getProductsByCategoryId")
    public Result getProductsByCategoryId(
            @RequestParam("cid") String cid,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize
         ){
        //通过cid获取商品列表
        Map queryMap = new HashMap<>();
        queryMap.put("sort_id", cid);
        queryMap.put("status", "1");
        IPage<StoreProduct> sortProducts = storeProductService.page(new Page<StoreProduct>(pageNum, pageSize), new QueryWrapper<StoreProduct>().allEq(queryMap));
        List<StoreProduct> records = sortProducts.getRecords();

        List productList = new ArrayList();
        for (StoreProduct record : records) {
            Map queryProductMap = new HashMap<>();
            queryProductMap.put("id", record.getId());
            queryProductMap.put("productName", record.getProductName());
            queryProductMap.put("productPrice", record.getProductPrice());
            queryProductMap.put("productDesc", record.getProductDesc());
            queryProductMap.put("productDetail", record.getProductDetail());
            queryProductMap.put("sortId", record.getSortId());
            //获取图片地址
            List<StoreProductImage> productImageList = storeProductImageService.list(new QueryWrapper<StoreProductImage>().eq("product_id", record.getId()));
            ArrayList<Object> imageArr = new ArrayList<>();
            for (StoreProductImage storeProductImage : productImageList) {
                imageArr.add(storeProductImage.getImageUrl());
            }
            queryProductMap.put("imageUrl", imageArr);
            //获取商品状态（浏览量）
            StoreStatus productStatus = storeStatusService.getOne(new QueryWrapper<StoreStatus>().eq("product_id", record.getId()));
            queryProductMap.put("viewCount", productStatus.getViewCount());
            productList.add(queryProductMap);
        }
        return Result.ok().data("lists", productList);
    }

    //获取在售商品
    @GetMapping("/getProductById")
    public Result getProductById(@RequestParam("pid") String pid){
        //获取商品
        StoreProduct product = storeProductService.getById(pid.trim());
        if(product != null) {
            //构造json
            Map queryProductMap = new HashMap<>();
            queryProductMap.put("id", product.getId());
            queryProductMap.put("productName", product.getProductName());
            queryProductMap.put("productPrice", product.getProductPrice());
            queryProductMap.put("productDesc", product.getProductDesc());
            queryProductMap.put("productDetail", product.getProductDetail());
            queryProductMap.put("memberId", product.getMemberId());
            queryProductMap.put("sortId", product.getSortId());
            //获取图片地址
            List<StoreProductImage> productImageList = storeProductImageService.list(new QueryWrapper<StoreProductImage>().eq("product_id", product.getId()));
            ArrayList<Object> imageArr = new ArrayList<>();
            for (StoreProductImage storeProductImage : productImageList) {
                imageArr.add(storeProductImage.getImageUrl());
            }
            queryProductMap.put("imageUrl", imageArr);
            //获取商品状态
            StoreStatus productStatus = storeStatusService.getOne(new QueryWrapper<StoreStatus>().eq("product_id", product.getId()));
            queryProductMap.put("viewCount", productStatus.getViewCount());
            queryProductMap.put("productCount", productStatus.getProductCount());
            return Result.ok().data("product", queryProductMap);
        }else {
            return Result.error();
        }
    }

    //上架商品
    @PostMapping("/newStock")
    public Result newStock(@RequestBody JSONObject jsonObject){
        //解析数据
        String productName = jsonObject.getString("productName");
        String productDetail = jsonObject.getString("productDetail");
        JSONArray sortIds = jsonObject.getJSONArray("sortId");
        Object[] sortIdArr = sortIds.toArray();
        String sortId = sortIdArr[sortIdArr.length - 1].toString();
        JSONArray jsonImageUrls = jsonObject.getJSONArray("imageUrl");
        List<String> imageUrls = new ArrayList<>();
        if(jsonImageUrls != null) {
            //文件路径
            Object[] imageUrlsArr = jsonImageUrls.toArray();
            for (Object imageUrl : imageUrlsArr) {
                imageUrls.add(imageUrl.toString());
            }
        }
        String productDesc = jsonObject.getString("productDesc");
        Double productPrice = jsonObject.getDouble("productPrice");
        String memberId = jsonObject.getString("memberId");
        Integer productCount = jsonObject.getInteger("productCount");
        //构建对象StoreProduct
        StoreProduct storeProduct = new StoreProduct();
        storeProduct.setProductDesc(productDesc);
        storeProduct.setProductPrice(BigDecimal.valueOf(productPrice));
        storeProduct.setProductName(productName);
        storeProduct.setProductDetail(productDetail);
        storeProduct.setSortId(sortId);
        storeProduct.setMemberId(memberId);
        storeProduct.setStatus(1);

        //保存商品
        String pid = storeProductService.saveProductData(storeProduct);
        if(pid != null && !"".equals(pid)) {
            //保存成功
            //构建对象StoreStatus
            StoreStatus storeStatus = new StoreStatus();
            storeStatus.setProductCount(productCount.longValue());
            storeStatus.setProductId(pid);
            boolean addStatusSuccess = storeStatusService.save(storeStatus);
            if(!addStatusSuccess) {
                return Result.error();
            }
            //构建对象StoreProductImage
            for (String imageUrl : imageUrls) {
                StoreProductImage storeProductImage = new StoreProductImage();
                storeProductImage.setProductId(pid);//设置商品id
                storeProductImage.setImageUrl(imageUrl);
                int flag = FileStorageUtil.RunTimeElement();
                if(flag ==0) {
                    String imageName = imageUrl.substring(imageUrl.lastIndexOf("\\") + 1);
                    storeProductImage.setImageName(imageName);
                }else if(flag == 1) {
                    String[] split = imageUrl.split("/");
                    String imageName = split[split.length - 1];
                    storeProductImage.setImageName(imageName);
                }
                storeProductImageService.save(storeProductImage);
            }
            //成功添加
            return  Result.ok();
        }
        return  Result.error();
    }

    //上传图片
    @PostMapping("/uploadImages")
    public Result uploadProductImages(@RequestParam("files") MultipartFile[] files) throws IOException {
        if( files == null || files.length ==0 ) {
            //无文件上传
            return  Result.error();
        }
        //上传文件
        List<String> imageUrls = FileStorageUtil.saveImageToLocal(files);
        if(imageUrls.size() == files.length) {
            //全部文件图片已经上传
            return  Result.ok().data("imageUrls", imageUrls);
        }else {
            return Result.error();
        }
    }

    //更新商品的浏览量
    @GetMapping("/refreshViewCount")
    public Result refreshProductViewCount(@RequestParam("productId") String productId) {
        //查询商品
        StoreStatus productStatus = storeStatusService.getOne(new QueryWrapper<StoreStatus>().eq("product_id", productId));
        Long viewCount = productStatus.getViewCount();
        productStatus.setViewCount(++viewCount);
        boolean isSuccess = storeStatusService.updateById(productStatus);
        if(isSuccess) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //下架商品
    @GetMapping("/soldOut")
    public Result soldOutProduct(@RequestParam("pid") String pid, @RequestParam("mid") String mid) {
        //查询该用户id下是否有该商品
        StoreProduct saleProduct = storeProductService.getById(pid);
        if(saleProduct != null) {
            if(saleProduct.getMemberId().equals(mid.trim())) {
                //更新商品status
                saleProduct.setStatus(0);
                boolean isSuccess = storeProductService.updateById(saleProduct);
                if(isSuccess) {
                    return Result.ok();
                }else {
                    return Result.error();
                }
            }else {
                //没有该商品,没有权限
                return Result.error();
            }
        }else {
            return Result.error();
        }
    }

    //上架商品
    @GetMapping("/soldUp")
    public Result soldUpProduct(@RequestParam("pid") String pid, @RequestParam("mid") String mid) {
        //查询该用户id下是否有该商品
        StoreProduct saleProduct = storeProductService.getById(pid);
        if(saleProduct != null) {
            if(saleProduct.getMemberId().equals(mid.trim())) {
                //更新商品status
                saleProduct.setStatus(1);
                boolean isSuccess = storeProductService.updateById(saleProduct);
                if(isSuccess) {
                    return Result.ok();
                }else {
                    return Result.error();
                }
            }else {
                //没有该商品,没有权限
                return Result.error();
            }
        }else {
            return Result.error();
        }
    }


    //搜索商品推荐
    @GetMapping("/searchProductRecommend")
    public Result searchProductRecommend() {
        //查询商品列表
        List<StoreProduct> recommendList = storeProductService.list(new QueryWrapper<StoreProduct>().eq("status", "1"));
        //获取商品名称
        List<String> listStr = new ArrayList<>();
        for (StoreProduct storeProduct : recommendList) {
            listStr.add(storeProduct.getProductName());
        }
        return Result.ok().data("lists", listStr);
    }


    //通过关键字搜索商品数据
    @GetMapping("/searchProducts")
    public Result searchProducts(@RequestParam("query") String query) {
        //查询商品列表
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("status", "1");
        List<StoreProduct> queryProductList = storeProductService.list(new QueryWrapper<StoreProduct>().allEq(queryMap).like("product_name",query));

        List productList = new ArrayList();
        for (StoreProduct record : queryProductList) {
            Map queryProductMap = new HashMap<>();
            queryProductMap.put("id", record.getId());
            queryProductMap.put("productName", record.getProductName());
            queryProductMap.put("productPrice", record.getProductPrice());
            queryProductMap.put("productDesc", record.getProductDesc());
            //获取图片地址
            List<StoreProductImage> productImageList = storeProductImageService.list(new QueryWrapper<StoreProductImage>().eq("product_id", record.getId()));
            queryProductMap.put("imageUrl", productImageList.get(0).getImageUrl());
            //获取商品状态（浏览量）
            StoreStatus productStatus = storeStatusService.getOne(new QueryWrapper<StoreStatus>().eq("product_id", record.getId()));
            queryProductMap.put("viewCount", productStatus.getViewCount());
            queryProductMap.put("buyCount", productStatus.getBuyCount());
            productList.add(queryProductMap);
        }
        return Result.ok().data("lists", productList);
    }
}

