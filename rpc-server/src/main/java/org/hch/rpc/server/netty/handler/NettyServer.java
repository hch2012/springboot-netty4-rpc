package org.hch.rpc.server.netty.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.hch.rpc.common.config.ApplicationProperties;
import org.hch.rpc.common.protocol.Request;
import org.hch.rpc.common.protocol.codec.MessageDecoder;
import org.hch.rpc.common.protocol.codec.MessageEncoder;
import org.hch.rpc.common.protocol.marshalling.Marshalling;
import org.hch.rpc.server.map.ServiceMap;
import org.hch.rpc.server.register.ServiceRegister;

import java.io.IOException;

/**
 * Created by chenghao on 9/19/16.
 */

public class NettyServer {
    private EventLoopGroup bossGroup =null;
    private EventLoopGroup workerGroup =null;
    private Marshalling marshalling;
    private ApplicationProperties applicationProperties;
    private ServiceMap serviceMap;
    private ServiceRegister serviceRegister;
    public NettyServer(Marshalling marshalling, ApplicationProperties applicationProperties, ServiceMap serviceMap, ServiceRegister serviceRegister){
        this.applicationProperties=applicationProperties;
        this.marshalling=marshalling;
        this.serviceMap=serviceMap;
        this.serviceRegister=serviceRegister;
    }

   public void start() throws Exception {
       bossGroup = new NioEventLoopGroup();
       workerGroup = new NioEventLoopGroup();
       ServerBootstrap b = new ServerBootstrap();
       b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
               .option(ChannelOption.SO_BACKLOG, 100)
               .handler(new LoggingHandler(LogLevel.INFO))
               .childHandler(new ChannelInitializer<SocketChannel>() {
                   @Override
                   public void initChannel(SocketChannel ch)
                           throws IOException {
                       ch.pipeline().addLast(new MessageDecoder(marshalling, Request.class));
                       ch.pipeline().addLast(new MessageEncoder(marshalling));
                       ch.pipeline().addLast(new ServiceHandler(serviceMap));
                   }
               });
       // 绑定端口，同步等待成功
       b.bind(applicationProperties.getAddr(),applicationProperties.getPort()).sync();
       System.out.println("Netty server start ok : "
               + (applicationProperties.getAddr() + " : " + applicationProperties.getPort()));
       serviceRegister.register();
   }
}
