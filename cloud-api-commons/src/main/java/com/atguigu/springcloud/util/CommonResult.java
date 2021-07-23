package com.atguigu.springcloud.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/20 15:22
 * Note: CommonResult<T> 表示返回的类型由T来决定，里面装了什么类型返回什么类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public CommonResult(Integer code, String msg){
        this(code,msg,null);
    }
}
