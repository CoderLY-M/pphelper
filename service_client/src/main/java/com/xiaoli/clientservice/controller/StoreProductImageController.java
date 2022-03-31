package com.xiaoli.clientservice.controller;


import com.xiaoli.commonutils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品图片表 前端控制器
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/product")
public class StoreProductImageController {
    //获取所有的
    @GetMapping("/swiperImages")
    public Result getCategories(){
        List<Map> list = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("id","0");
        map1.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map1.put("imageName","数码产品");
        map1.put("viewCount",343);
        list.add(map1);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("id","1");
        map2.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map2.put("imageName","生活用品");
        map2.put("viewCount",232);
        list.add(map2);
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("id","2");
        map3.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map3.put("imageName","服饰");
        map3.put("viewCount",343);
        list.add(map3);
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("id","3");
        map4.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map4.put("imageName","女士");
        map4.put("viewCount",23);
        list.add(map4);
        HashMap<String, Object> map5= new HashMap<>();
        map5.put("id","4");
        map5.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map5.put("imageName","男士");
        map5.put("viewCount",23);
        list.add(map5);
        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("id","5");
        map6.put("imageUrl","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map6.put("imageName","母婴");
        map6.put("viewCount",342323);
        list.add(map6);

        return Result.ok().data("lists",list);
    }
}

