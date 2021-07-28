package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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

    @GetMapping(value = "/cosnumer/fallback/{id}")
    @SentinelResource(value = "fallback")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        CommonResult<Payment> result = paymentControllerService.paymentSQL(id);

        if(id == 4){
            throw new IllegalArgumentException("非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("该ID没有对应记录，空指针异常");
        }
        return result;
    }
}
