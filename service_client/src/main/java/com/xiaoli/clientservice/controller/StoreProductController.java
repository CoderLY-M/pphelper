package com.xiaoli.clientservice.controller;


import com.xiaoli.commonutils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //通过商品类别获取商品的数据列表
    @GetMapping("/getProductsByCategoryId")
    public Result getProductsByCategoryId(
            @RequestParam("cid") String cid,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize
         ){
        if(pageNum <= 5) {
            List<Map> list = new ArrayList<>();
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("id","0");
            List imageList = new ArrayList();
            imageList.add("https://w.wallhaven.cc/full/g7/wallhaven-g7lm7q.jpg");
            imageList.add("https://w.wallhaven.cc/full/m9/wallhaven-m9mzr8.jpg");
            imageList.add("https://w.wallhaven.cc/full/72/wallhaven-72k6ze.jpg");
            map1.put("imageUrl",imageList);
            map1.put("productName","苹果11");
            map1.put("productPrice",5600.00);
            map1.put("productDesc","");
            map1.put("sortId","");
            map1.put("productDetail","");
            map1.put("viewCount",565);
            list.add(map1);
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("id","1");
            map2.put("imageUrl",imageList);
            map2.put("productName","苹果12");
            map2.put("productPrice",5300.00);
            map2.put("viewCount",565);
            map2.put("productDesc","");
            map2.put("sortId","");
            map2.put("productDetail","");
            list.add(map2);
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("id","2");
            List imageList2 = new ArrayList();
            imageList2.add("https://w.wallhaven.cc/full/1k/wallhaven-1km5dg.jpg");
            imageList2.add("https://w.wallhaven.cc/full/m9/wallhaven-m9mzr8.jpg");
            imageList2.add("https://w.wallhaven.cc/full/72/wallhaven-72k6ze.jpg");
            map3.put("imageUrl",imageList2);
            map3.put("productName","苹果13");
            map3.put("productPrice",9900.00);
            map3.put("viewCount",454545);
            map3.put("productDesc","");
            map3.put("sortId","");
            map3.put("productDetail","");
            list.add(map3);
            HashMap<String, Object> map4 = new HashMap<>();
            map4.put("id","3");
            map4.put("imageUrl",imageList);
            map4.put("productName","苹果11");
            map4.put("productPrice",5600.00);
            map4.put("viewCount",4444);
            map4.put("productDesc","");
            map4.put("sortId","");
            map4.put("productDetail","");
            list.add(map4);
            HashMap<String, Object> map5= new HashMap<>();
            map5.put("id","4");
            map5.put("imageUrl",imageList2);
            map5.put("productName","VIVO FindX");
            map5.put("productPrice",5600.00);
            map5.put("viewCount",23);
            map5.put("productDesc","");
            map5.put("sortId","");
            map5.put("productDetail","");
            list.add(map5);
            HashMap<String, Object> map6= new HashMap<>();
            map6.put("id","4");
            map6.put("imageUrl",imageList);
            map6.put("productName","VIVO FindX");
            map6.put("productPrice",5600.00);
            map6.put("viewCount",23);
            map6.put("productDesc","");
            map6.put("sortId","");
            map6.put("productDetail","");
            list.add(map6);
            HashMap<String, Object> map10= new HashMap<>();
            map10.put("id","4");
            map10.put("imageUrl",imageList2);
            map10.put("productName","VIVO FindX");
            map10.put("productPrice",5600.00);
            map10.put("viewCount",23);
            map10.put("productDesc","");
            map10.put("sortId","");
            map10.put("productDetail","");
            list.add(map10);
            HashMap<String, Object> map7= new HashMap<>();
            map7.put("id","4");
            map7.put("imageUrl",new ArrayList<>());
            map7.put("productName","VIVO FindX");
            map7.put("productPrice",5600.00);
            map7.put("viewCount",23);
            map7.put("productDesc","");
            map7.put("sortId","");
            map7.put("productDetail","");
            list.add(map7);
            HashMap<String, Object> map8= new HashMap<>();
            map8.put("id","4");
            map8.put("imageUrl",imageList2);
            map8.put("productName","VIVO FindX");
            map8.put("productPrice",5600.00);
            map8.put("viewCount",23);
            map8.put("productDesc","");
            map8.put("sortId","");
            map8.put("productDetail","");
            list.add(map8);
            HashMap<String, Object> map9= new HashMap<>();
            map9.put("id","4");
            map9.put("imageUrl",imageList);
            map9.put("productName","VIVO FindX");
            map9.put("productPrice",5600.00);
            map9.put("viewCount",23);
            map9.put("productDesc","");
            map9.put("sortId","");
            map9.put("productDetail","");
            list.add(map9);
            return Result.ok().data("lists",list);
        }else {
            List<Map> list = new ArrayList<>();
            return Result.ok().data("lists",list);
        }
    }

    //获取在售商品
    @GetMapping("/getProductById")
    public Result getProductById(@RequestParam("pid") String pid){
        HashMap<String, Object> map1 = new HashMap<>();
        if(pid.equals("0")) {
            map1.put("id","0");
            List imageList = new ArrayList();
            imageList.add("https://w.wallhaven.cc/full/g7/wallhaven-g7lm7q.jpg");
            imageList.add("https://w.wallhaven.cc/full/m9/wallhaven-m9mzr8.jpg");
            imageList.add("https://w.wallhaven.cc/full/72/wallhaven-72k6ze.jpg");
            map1.put("imageUrl",imageList);
            map1.put("productName","苹果11");
            map1.put("productPrice",5600.00);
            map1.put("viewCount",565);
            map1.put("sortId","");
            map1.put("productDesc","苹果11是苹果公司2020年推出的商品,是一款伟大的产品苹果11是苹果公司2020年推出的商品,是一款伟大的产品苹果11是苹果公司2020年推出的商品,是一款伟大的产品");
            map1.put("productDetail","<h1>苹果11是苹果公司2020年推出的商品<h1>");
            map1.put("productCount",78);
        }else {
            map1.put("id","1");
            List imageList = new ArrayList();
            imageList.add("https://w.wallhaven.cc/full/1k/wallhaven-1km5dg.jpg");
            imageList.add("https://w.wallhaven.cc/full/m9/wallhaven-m9mzr8.jpg");
            imageList.add("https://w.wallhaven.cc/full/72/wallhaven-72k6ze.jpg");
            map1.put("imageUrl",imageList);
            map1.put("productName","苹果12");
            map1.put("productPrice",9000.00);
            map1.put("viewCount",3234);
            map1.put("sortId","");
            map1.put("productDesc","苹果12是苹果公司2020年推出的商品");
            map1.put("productDetail","苹果12是苹果公司2020年推出的商品");
            map1.put("productCount",44);
        }
        return Result.ok().data("product",map1);
    }

}

