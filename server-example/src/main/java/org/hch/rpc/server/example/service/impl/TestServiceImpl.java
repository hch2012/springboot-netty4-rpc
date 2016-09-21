package org.hch.rpc.server.example.service.impl;

import org.hch.rpc.server.api.example.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by chenghao on 9/9/16.
 */
@Service
public class TestServiceImpl implements TestService{

    @Override
    public String test() {
        System.out.println("test");
        return "hello";
    }
}
