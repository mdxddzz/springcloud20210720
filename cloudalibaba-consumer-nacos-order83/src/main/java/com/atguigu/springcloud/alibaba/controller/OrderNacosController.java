package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.service.OrderNacosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/27 13:30
 * Note:
 */
@RestController
@Slf4j
public class OrderNacosController {
    @Autowired
    OrderNacosService orderNacosService;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id){
        return orderNacosService.getPayment(id);
    }
}
