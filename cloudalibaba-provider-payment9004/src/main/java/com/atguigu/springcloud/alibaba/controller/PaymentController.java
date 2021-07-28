package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.util.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/28 17:11
 * Note:
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L,new Payment(1L, "cdce5cbc-7329-4e52-b07e-a93b0594fd9c"));
        hashMap.put(2L,new Payment(2L, "49c77bf8-8f8d-427e-9286-22ee525ce731"));
        hashMap.put(3L,new Payment(3L, "62c12d90-2001-4de0-9cc3-c5f986caccaa"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200,"from mysql,server port:"+serverPort,payment);
    }
}
