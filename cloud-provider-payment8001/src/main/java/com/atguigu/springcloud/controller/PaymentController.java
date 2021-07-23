package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.atguigu.springcloud.util.CommonResult;
import com.mysql.cj.util.TimeUtil;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/20 16:05
 * Note:
 */
@RestController
@Slf4j
public class PaymentController{
    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        boolean result = paymentService.save(payment);
        log.info("插入结果:"+result);

        if(result == true){
            return new CommonResult(200,"插入数据库成功"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败"+serverPort,result);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getById(id);
        log.info("查询结果:"+payment);

        if(payment != null){
            return new CommonResult(200,"查询成功"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询不到ID为"+id+"的对应记录"+serverPort,null);
        }
    }

    @GetMapping("/payment/getAll")
    public CommonResult getPaymentList(){
        List<Payment> paymentList = paymentService.list(null);
        log.info("查询结果:"+paymentList);

        if(paymentList != null){
            return new CommonResult(200,"查询成功"+serverPort,paymentList);
        }else {
            return new CommonResult(444,"查询不到对应记录"+serverPort,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        /*try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        return serverPort;
    }
}
