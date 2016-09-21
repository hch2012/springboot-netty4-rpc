package org.hch.rpc.server.example.config;

import org.hch.rpc.server.api.example.service.HelloService;
import org.hch.rpc.server.api.example.service.TestService;
import org.hch.rpc.server.map.ServiceMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenghao on 9/19/16.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ServerConfig {

    @Bean
    public ServiceMap serviceMap(HelloService helloService,TestService testService){
        ServiceMap serviceMap=new ServiceMap();
        serviceMap.addService(HelloService.class.getName(),helloService);
        serviceMap.addService(TestService.class.getName(),testService);
        return serviceMap;
    }

}
