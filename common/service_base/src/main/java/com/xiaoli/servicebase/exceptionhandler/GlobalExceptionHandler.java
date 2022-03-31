package com.xiaoli.servicebase.exceptionhandler;

import com.xiaoli.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author xiaoli
 * @Date 2022/3/26 11:34
 * @Version 1.0
 */
//全局处理异常类
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 统一错误处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error();
    }
}
