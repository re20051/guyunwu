package com.example.guyunwu.model.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局统一返回结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    private Integer status;
    private String message;
    private T data;

    public static <T> Result<T> ok(){
        return new Result<>(200,"ok",null);
    }

    public static <T> Result<T> ok(String message, T data){
        return new Result<>(200,message,data);
    }

    public static <T> Result<T> ok(String message){
        return Result.ok(message,null);
    }

    public static <T> Result<T> error(){
        return new Result<>(400,"error",null);
    }

    public static <T> Result<T> error(String message, T data){
        return new Result<>(400,message,data);
    }

    public static <T> Result<T> error(String message){
        return Result.error(message,null);
    }
}

