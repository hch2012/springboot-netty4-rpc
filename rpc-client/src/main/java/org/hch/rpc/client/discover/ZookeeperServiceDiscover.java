package org.hch.rpc.client.discover;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.hch.rpc.client.manage.Handler;
import org.hch.rpc.common.config.ApplicationProperties;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by chenghao on 9/8/16.
 */
public class ZookeeperServiceDiscover implements ServiceDiscover {
    private CuratorFramework curatorFramework;
    private ApplicationProperties applicationProperties;
    private ConcurrentMap<String,ConcurrentMap<String,String>> map=null;
    private Handler addHandler;
    private Handler removeHandler;

    public ZookeeperServiceDiscover(CuratorFramework curatorFramework,ApplicationProperties applicationProperties){
        this.curatorFramework=curatorFramework;
        this.applicationProperties=applicationProperties;
    }

    @Override
    public Map<String, Map<String,String>> discover() {
        Map<String,Map<String,String>> typeMap=new HashMap<>();
        for(String key:map.keySet()){
            typeMap.put(key,Collections.unmodifiableMap(map.get(key)));
        }
        return typeMap;
    }

    public void init() throws Exception {
        map=new ConcurrentHashMap<>();
        List<String> pathList=curatorFramework.getChildren().forPath(applicationProperties.getRegisterPath());
        for (String path:pathList){
            String type=getType(path);
            String data=new String(curatorFramework.getData().forPath(applicationProperties.getFullPath(path)));
            addToMap(type,path,data);
        }
    }
    @Override
    public void watchService() {
        PathChildrenCache cache =new PathChildrenCache(curatorFramework,applicationProperties.getRegisterPath(),true);
        cache.getListenable().addListener((client,event)->{
            switch (event.getType()) {
                case CHILD_ADDED:
                    dealAdd(event);
                    break;
                case CHILD_REMOVED:
                    dealRemove(event);
                    break;
                default:
                    break;
            }
        });
    }

    private void dealAdd(PathChildrenCacheEvent event) {
        String type=getType(event.getData().getPath());
        addToMap(type,event.getData().getPath(),new String(event.getData().getData()));
        if(addHandler!=null)
            addHandler.handle(type,event.getData().getPath(),new String(event.getData().getData()));
    }
    private void dealRemove(PathChildrenCacheEvent event) {
        String type=getType(event.getData().getPath());
        removeFromMap(type,event.getData().getPath());
        if(removeHandler!=null)
            removeHandler.handle(getType(event.getData().getPath()),event.getData().getPath(),new String(event.getData().getData()));
    }
    private void addToMap(String type,String name,String url){
        map.putIfAbsent(type,new ConcurrentHashMap<>());
        map.get(type).put(name,url);
    }
    private void removeFromMap(String type,String name){
        //防止某个service上线马上下线,有可能导致removeFromMap先运行
        map.putIfAbsent(type,new ConcurrentHashMap<>());
        map.get(type).remove(name);
    }

    @Override
    public ServiceDiscover bindAddHandler(Handler handler){
        addHandler=handler;
        return this;
    }
    @Override
    public ServiceDiscover bindRemoveHandler(Handler handler){
        removeHandler=handler;
        return this;
    }


    private String getType(String name){
        int index=name.lastIndexOf("_");
        return name.substring(0,index);
    }

}
