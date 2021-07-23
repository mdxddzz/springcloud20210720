package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/23 13:08
 * Note:
 */
@Service
public class PaymentHystrixFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----------PaymentFallbackService fall back  paymentInfo_OK,/(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----------PaymentFallbackService fall back  paymentInfo_TimeOut,/(ㄒoㄒ)/~~";
    }
}
