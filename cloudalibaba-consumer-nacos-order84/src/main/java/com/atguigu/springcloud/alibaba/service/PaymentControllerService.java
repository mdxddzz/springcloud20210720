package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.service.myFallback.PaymentFallbackService;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/28 17:41
 * Note:
 */
@FeignClient(name = "${service-url.nacos-user-service}",fallback = PaymentFallbackService.class)
public interface PaymentControllerService {
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
