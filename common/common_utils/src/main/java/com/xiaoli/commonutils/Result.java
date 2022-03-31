package com.xiaoli.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xiaoli
 * @Date 2022/3/25 19:38
 * @Version 1.0
 */
//结果数据
@Data
public class Result {
    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "结果数据")
    private Map<String,Object> data = new HashMap<>();

    private Result() {}

    //成功
    public static Result ok(){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    //失败
    public static Result error(){
        Result result = new Result();
        result.setCode(ResultCode.ERROR);
        result.setSuccess(false);
        result.setMessage("失败");
        return result;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }


}
