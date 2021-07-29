package com.atguigu.springcloud.alibaba.service.myFallback;

import com.atguigu.springcloud.alibaba.service.PaymentControllerService;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.util.CommonResult;

/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/29 10:15
 * Note:
 */
public class PaymentFallbackService implements PaymentControllerService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回,------PaymentFallbackService",new Payment(id,"error service"));
    }
}
