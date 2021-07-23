package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/23 9:25
 * Note:
 */
@Service
public class PaymentHystrixService {
    public String paymentInfo_OK(Integer id){   //正常访问的方法
        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_OK,id : "+id+"\t"+"O(∩_∩)O";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){   //正常访问的方法
//        int age = 10/0;
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_TimeOut,id : "+id+"\t"+"O(∩_∩)O"+"耗时:"+timeNumber+"s";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"  系统繁忙或运行报错,请稍后再试,id : "+id+"\t"+"/(ㄒoㄒ)/~~";
    }


    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value="true"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),  //请求总数阈值
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60"),    //错误失败率阈值
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value="10000")  //根据最近收到的10s请求数据来判断
    })
    public String paymentCircuitBreaker(@PathVariable("id") Long id){
        if(id<0){
            throw new RuntimeException("*********id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号:"+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Long id){
        return "id不能为负数,请稍后再试，/(ㄒoㄒ)/~~ id:"+id;
    }
}
