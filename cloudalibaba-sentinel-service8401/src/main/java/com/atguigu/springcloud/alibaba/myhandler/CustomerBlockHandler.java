package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/28 16:44
 * Note:
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"按客户自定义,global handlerException-----1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444,"按客户自定义,global handlerException-----2");
    }
}
