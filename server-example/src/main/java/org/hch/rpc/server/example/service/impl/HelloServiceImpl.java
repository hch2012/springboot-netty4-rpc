package org.hch.rpc.server.example.service.impl;

import org.hch.rpc.server.api.example.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by chenghao on 9/9/16.
 */
@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello() {
        System.out.println("hello");
        return "hello";
    }
}
