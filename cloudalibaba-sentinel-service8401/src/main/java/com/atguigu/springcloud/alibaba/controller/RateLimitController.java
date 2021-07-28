package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/28 14:44
 * Note:
 */
@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2021L,"serial001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")   //根据URL限流会返回默认的限流处理信息
    @SentinelResource(value = "byUrl")  //按资源名称限流,可以返回自己自定义的限流处理信息,
    // 若没有配置指定的自定义限流处理信息(参数blockHandler)则会返回500错误信息页面
    public CommonResult byUrl(){
        return new CommonResult(200,"按url限流测试OK",new Payment(2021L,"serial002"));
    }


    //配置自定义通用限流处理逻辑
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按客户自定义",new Payment(2021L,"serial003"));
    }

}
