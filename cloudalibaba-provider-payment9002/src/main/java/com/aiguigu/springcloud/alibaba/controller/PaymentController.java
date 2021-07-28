package com.aiguigu.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/27 12:48
 * Note:
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id){
        return "nacos registry,serverPort : "+serverPort+"\t id :"+id;
    }
}
