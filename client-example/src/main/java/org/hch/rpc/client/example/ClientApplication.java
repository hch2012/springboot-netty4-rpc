package org.hch.rpc.client.example;

import org.hch.rpc.server.api.example.service.HelloService;
import org.hch.rpc.server.api.example.service.TestService;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by chenghao on 9/7/16.
 */
@SpringBootApplication
public class ClientApplication {
    public static void main(String args[]){
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ClientApplication.class)
                .registerShutdownHook(true)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);

        HelloService helloService=context.getBean(HelloService.class);
        System.out.println("hello result is :"+helloService.hello());
        System.out.println(context.getBean(TestService.class).test());
        //WorldService worldService=context.getBean(WorldService.class);
        //worldService.world();
    }


}
