package org.hch.rpc.client.example.config;

import org.hch.rpc.client.proxy.ServiceProxy;
import org.hch.rpc.server.api.example.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

/**
 * Created by chenghao on 9/8/16.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ServiceConfiguration {
    @Autowired
    private ServiceProxy serviceProxy;

    @Bean
    public HelloService helloService(){
        return (HelloService)Proxy.newProxyInstance(HelloService.class.getClassLoader(),new Class[]{HelloService.class},serviceProxy);
    }
}
