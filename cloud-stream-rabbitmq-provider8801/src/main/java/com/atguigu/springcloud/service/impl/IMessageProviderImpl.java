package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;


/**
 * @Author: Zjn ; Bring it on !!!
 * @Date: 2021/7/26 15:57
 * Note:
 */
@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {

    @Autowired
    MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("**********serial:"+serial);
        return null;
    }
}
