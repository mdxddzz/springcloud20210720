package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/21 15:18
 * Note:
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
//    @LoadBalanced   //当配置多台机子提供服务时需要提供一种默认的负载均衡方式来选择，否则无法自动选择
    public RestTemplate getRestTemlate(){
        return new RestTemplate();
    }
}
