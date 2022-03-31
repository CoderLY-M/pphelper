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
 * 商品分类 前端控制器
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/category")
public class StoreCategoryController {

    //获取所有的一届分类列表
    @GetMapping("/getCategories")
    public Result getCategories(){
        List<Map> list = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("id","0");
        map1.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map1.put("title","数码产品");
        map1.put("parentId","0");
        HashMap<String, Object> subMap10 = new HashMap<>();
        subMap10.put("id","2323a");
        subMap10.put("title","手机");
        subMap10.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map1.put("subCategories",subMap10);
        HashMap<String, Object> subMap11 = new HashMap<>();
        subMap11.put("id","2323b");
        subMap11.put("title","电脑");
        subMap11.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        List<Map> list11 = new ArrayList<>();
        HashMap<String, Object> subMap12 = new HashMap<>();
        subMap12.put("id","2323c");
        subMap12.put("title","相机");
        subMap12.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map1.put("subCategories",subMap12);
        HashMap<String, Object> subMap13 = new HashMap<>();
        subMap13.put("id","2323d");
        subMap13.put("title","MP4");
        subMap13.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map1.put("subCategories",subMap13);
        list11.add(subMap10);
        list11.add(subMap11);
        list11.add(subMap12);
        list11.add(subMap13);
        map1.put("subCategories",list11);
        list.add(map1);

        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("id","1");
        map2.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map2.put("title","生活用品");
        map2.put("parentId","0");
        List<Map> list12 = new ArrayList<>();
        map2.put("subCategories",list12);
        list.add(map2);

        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("id","2");
        map3.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map3.put("title","服饰");
        map3.put("parentId","0");
        list.add(map3);
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("id","3");
        map4.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map4.put("title","女士");
        map4.put("parentId","0");
        list.add(map4);
        HashMap<String, Object> map5= new HashMap<>();
        map5.put("id","4");
        map5.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map5.put("title","男士");
        map5.put("parentId","0");
        list.add(map5);
        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("id","5");
        map6.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map6.put("title","母婴");
        map6.put("parentId","0");
        list.add(map6);
        HashMap<String, Object> map9 = new HashMap<>();
        map9.put("id","5");
        map9.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map9.put("title","母婴");
        map9.put("parentId","0");
        list.add(map9);
        HashMap<String, Object> map7 = new HashMap<>();
        map7.put("id","5");
        map7.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map7.put("title","母婴");
        map7.put("parentId","0");
        list.add(map7);
        HashMap<String, Object> map8 = new HashMap<>();
        map8.put("id","5");
        map8.put("icon","https://w.wallhaven.cc/full/72/wallhaven-72q1ly.jpg");
        map8.put("title","母婴");
        map8.put("parentId","0");
        list.add(map8);
        return Result.ok().data("lists",list);
    }
}

