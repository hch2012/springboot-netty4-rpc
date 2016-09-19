package org.hch.rpc.server.example.register;

import org.hch.rpc.server.register.ServiceRegister;

import javax.annotation.PostConstruct;

/**
 * Created by chenghao on 9/8/16.
 */
//@Component
public class CustomRegister implements ServiceRegister{
    @Override
    @PostConstruct
    public void register() throws Exception {
        System.out.println("CustomRegister");
    }
}
