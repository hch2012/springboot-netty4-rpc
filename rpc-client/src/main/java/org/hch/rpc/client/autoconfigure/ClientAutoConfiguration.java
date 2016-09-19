package org.hch.rpc.client.autoconfigure;

import org.apache.curator.framework.CuratorFramework;
import org.hch.rpc.client.discover.ServiceDiscover;
import org.hch.rpc.client.discover.ZookeeperServiceDiscover;
import org.hch.rpc.client.manage.ServerManager;
import org.hch.rpc.client.proxy.ServiceProxy;
import org.hch.rpc.client.route.RandomServiceRouter;
import org.hch.rpc.client.route.ServiceRouter;
import org.hch.rpc.common.config.ApplicationProperties;
import org.hch.rpc.common.protocol.marshalling.Marshalling;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenghao on 9/7/16.
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ClientAutoConfiguration {
    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    public ServiceDiscover serviceDiscover(CuratorFramework curatorFramework,ApplicationProperties applicationProperties){
        return new ZookeeperServiceDiscover(curatorFramework,applicationProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public ServiceRouter serviceRouter(){
        return new RandomServiceRouter();
    }

    @Bean(initMethod = "init")
    public ServerManager serviceManage(ServiceDiscover serviceDiscover, ServiceRouter serviceRouter, Marshalling marshalling){
        return new ServerManager(serviceDiscover,serviceRouter,marshalling);
    }

    @Bean
    public ServiceProxy serviceProxy(ServerManager serverManager){
        return new ServiceProxy(serverManager);
    }

}
