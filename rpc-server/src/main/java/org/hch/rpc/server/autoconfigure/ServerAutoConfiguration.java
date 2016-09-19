package org.hch.rpc.server.autoconfigure;

import org.apache.curator.framework.CuratorFramework;
import org.hch.rpc.common.config.ApplicationProperties;
import org.hch.rpc.common.protocol.marshalling.Marshalling;
import org.hch.rpc.server.map.ServiceMap;
import org.hch.rpc.server.netty.handler.NettyServer;
import org.hch.rpc.server.register.ServiceRegister;
import org.hch.rpc.server.register.ZookeeperServiceRegister;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by chenghao on 9/7/16.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ServerAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ServiceRegister serviceRegister(CuratorFramework curatorFramework, ApplicationProperties applicationProperties){
        return new ZookeeperServiceRegister(curatorFramework,applicationProperties);
    }

    @Bean(initMethod = "start" )
    public NettyServer nettyServer(ServiceMap map, Marshalling marshalling, ApplicationProperties applicationProperties, ServiceRegister serviceRegister){
        return new NettyServer(marshalling,applicationProperties,map,serviceRegister);

    }


}
