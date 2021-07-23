package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/23 16:05
 * Note:
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route_atguigu",
                        r -> r.path("/guonei")
                                .uri("http://news.baidu.com/guonei")).build();


    }
}
