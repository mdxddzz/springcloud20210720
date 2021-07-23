package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/22 9:31
 * Note:
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/zk")
    public CommonResult paymentZk(){
        return new CommonResult(200,"springcloud with zookeeper:"+serverPort+"  "+ UUID.randomUUID().toString());
    }
}
