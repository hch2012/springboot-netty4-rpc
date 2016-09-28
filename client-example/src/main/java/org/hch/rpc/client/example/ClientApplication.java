package org.hch.rpc.client.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by chenghao on 9/7/16.
 */
@SpringBootApplication
public class ClientApplication {
    private static Logger logger= LoggerFactory.getLogger(ClientApplication.class);
    public static void main(String args[]){
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ClientApplication.class)
                .registerShutdownHook(true)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }
}
