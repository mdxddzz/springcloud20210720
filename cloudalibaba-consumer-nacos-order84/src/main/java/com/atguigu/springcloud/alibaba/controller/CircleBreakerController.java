package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.service.PaymentControllerService;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/28 17:38
 * Note:
 */
@RestController
@Slf4j
public class CircleBreakerController {
    @Autowired
    PaymentControllerService paymentControllerService;

    @GetMapping(value = "/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")    //fallback只负责业务异常,返回一个兜底方法
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler")     //blockHandler只负责控制台配置违规，error page页面
    //blockHandler即触发了sentinel规则      综上所述，fallback用于处理内部异常，blockHandler用于处理sentinel控制台违规异常
    //假若同时配置blockHandler>fallback
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})  //exceptionsToIgnore用于忽略异常，即不会触发兜底fallback方法
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        CommonResult<Payment> result = paymentControllerService.paymentSQL(id);

        if(id == 4){
            throw new IllegalArgumentException("非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("该ID没有对应记录，空指针异常");
        }
        return result;
    }

    //本例是fallback
    public CommonResult handlerFallback(@PathVariable("id") Long id,Throwable e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(444,"兜底异常handlerFallback,exception内容 "+e.getMessage(),payment);
    }


    //本例是blockHandler
    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException blockException){
        Payment payment = new Payment(id, "null");
        return new CommonResult(445,"blockhandler-sentinel限流,无此流水:blockhandler  "+blockException.getMessage(),payment);
    }
}
