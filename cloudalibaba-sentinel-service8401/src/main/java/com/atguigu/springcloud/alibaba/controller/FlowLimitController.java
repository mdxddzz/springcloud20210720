package com.atguigu.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/28 10:41
 * Note:
 */
@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        return "-----------A";
    }

    @GetMapping("/testB")
    public String testB(){
        return "-----------B";
    }
}
