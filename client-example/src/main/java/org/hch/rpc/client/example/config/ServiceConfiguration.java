package org.hch.rpc.client.example.config;

import org.hch.rpc.client.proxy.ServiceStarter;
import org.hch.rpc.server.api.example.service.HelloService;
import org.hch.rpc.server.api.example.service.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenghao on 9/8/16.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ServiceConfiguration {
    @Bean
    public ServiceStarter serviceStarter(){
        return new ServiceStarter().startService(HelloService.class).startService(TestService.class);
    }
}
