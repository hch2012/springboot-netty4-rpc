package org.hch.rpc.client.proxy;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenghao on 9/21/16.
 */
public class ServiceStarter implements ApplicationListener<ContextRefreshedEvent> {
    private List<Class> list=new ArrayList<>();
    public ServiceStarter startService(Class clazz){
        list.add(clazz);
        return this;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ConfigurableApplicationContext  applicationContext=(ConfigurableApplicationContext) event.getApplicationContext();
        for(Class clazz:list){
            ServiceProxy serviceProxy=applicationContext.getBeanFactory().getBean(ServiceProxy.class);
            applicationContext.getBeanFactory().registerSingleton(clazz.getName(), Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},serviceProxy));
        }
    }
}
