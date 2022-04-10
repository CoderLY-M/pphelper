package com.xiaoli.clientservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoli.clientservice.entity.StoreCategory;
import com.xiaoli.clientservice.service.StoreCategoryService;
import com.xiaoli.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private StoreCategoryService storeCategoryService;

    //获取所有的一届分类列表
    @GetMapping("/getCategories")
    public Result getCategories(){
        //获取所有的分类
        List<StoreCategory> tempList = storeCategoryService.list(null);
        //构造父类
        List<HashMap<String,Object>> categoryList = new ArrayList<>();//父分类
        for (StoreCategory category : tempList) {
            if("0".equals(category.getParentId())) {
                //父分类
                HashMap<String,Object> categoryMap = new HashMap<>();
                categoryMap.put("id", category.getId());
                categoryMap.put("icon", category.getIcon());
                categoryMap.put("title", category.getTitle());
                categoryMap.put("parentId", category.getParentId());
                categoryMap.put("subCategories", new ArrayList<>());
                categoryList.add(categoryMap);
            }
        }
        //构造子类
        List<HashMap<String,Object>> subCategoryList = new ArrayList<>();//子分类
        for (StoreCategory category : tempList) {
            if(!"0".equals(category.getParentId())) {
                //子分类
                HashMap<String,Object> subCategoryMap = new HashMap<>();
                subCategoryMap.put("id", category.getId());
                subCategoryMap.put("icon", category.getIcon());
                subCategoryMap.put("title", category.getTitle());
                subCategoryMap.put("parentId", category.getParentId());
                subCategoryMap.put("subCategories", new ArrayList<>());
                subCategoryList.add(subCategoryMap);
            }
        }
        //添加子类与父类关系
        for (HashMap<String, Object> stringObjectHashMap : categoryList) {
            List tempSubCategoryList = new ArrayList();
            for (HashMap<String, Object> objectHashMap : subCategoryList) {
                if(stringObjectHashMap.get("id").equals(objectHashMap.get("parentId"))){
                    tempSubCategoryList.add(objectHashMap);
                }
            }
            stringObjectHashMap.put("subCategories", tempSubCategoryList);
        }
        return Result.ok().data("lists",categoryList);
    }

    //添加分类
    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody StoreCategory storeCategory) {
        //查询是否父类pid为'0',为0表示添加的是大类
        if ("0".equals(storeCategory.getParentId())) {
            //添加大类
            //查询是否已经有该类
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("title", storeCategory.getTitle());
            queryMap.put("parent_id", storeCategory.getParentId());
            StoreCategory category = storeCategoryService.getOne(new QueryWrapper<StoreCategory>().allEq(queryMap));
            if(category == null) {
                //没有改大类，可以进行添加
                boolean save = storeCategoryService.save(storeCategory);
                if (save) {
                    return  Result.ok();//添加大类成功
                }
            }
            return Result.error();
        }else {
            //添加小类
            //通过pid查询大类的数据,判断该大类下是否已经有该小类
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("title", storeCategory.getTitle());
            queryMap.put("parent_id", storeCategory.getParentId());
            //通过pid查询是否有该大类
            StoreCategory category = storeCategoryService.getOne(new QueryWrapper<StoreCategory>().eq("id", storeCategory.getParentId()));
            if(category == null) {
                //没有该大类，拒绝添加
                return  Result.error();
            }
            StoreCategory subCategory = storeCategoryService.getOne(new QueryWrapper<StoreCategory>().allEq(queryMap));
            if(subCategory == null) {
                //没有改小类，可以进行添加
                boolean save = storeCategoryService.save(storeCategory);
                if (save) {
                    return  Result.ok();//添加大类成功
                }
            }
            return Result.error();
        }
    }

}

