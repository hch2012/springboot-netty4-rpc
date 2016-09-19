package org.hch.rpc.server.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.hch.rpc.common.config.ApplicationProperties;

/**
 * Created by chenghao on 9/7/16.
 */
public class ZookeeperServiceRegister implements ServiceRegister {
    public ZookeeperServiceRegister(CuratorFramework curatorFramework, ApplicationProperties applicationProperties){
        this.curatorFramework=curatorFramework;
        this.applicationProperties=applicationProperties;
    }
    private CuratorFramework curatorFramework;
    private ApplicationProperties applicationProperties;
    private String path;

    public void register() throws Exception {
        if(curatorFramework.checkExists().forPath(applicationProperties.getRegisterPath())==null)
            curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(applicationProperties.getRegisterPath());
        path=curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(applicationProperties.getFullPath()+"_",applicationProperties.getHostUrl().getBytes());
    }
}
