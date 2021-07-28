package com.atguigu.springcloud.alibaba.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/27 13:28
 * Note:
 */
@FeignClient(name = "${feign.name}")
public interface OrderNacosService {
    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id);
}
