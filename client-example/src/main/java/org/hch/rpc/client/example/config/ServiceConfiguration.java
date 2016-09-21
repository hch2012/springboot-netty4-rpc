package org.hch.rpc.client.example.config;

import org.hch.rpc.client.proxy.ServiceProxy;
import org.hch.rpc.client.proxy.ServiceStarter;
import org.hch.rpc.server.api.example.service.HelloService;
import org.hch.rpc.server.api.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenghao on 9/8/16.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ServiceConfiguration {
    @Autowired
    private ServiceProxy serviceProxy;

    @Bean
    public ServiceStarter helloService(){
        return new ServiceStarter().startService(HelloService.class).startService(TestService.class);
    }
}
