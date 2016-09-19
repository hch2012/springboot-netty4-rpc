package org.hch.rpc.client.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.hch.rpc.client.manage.Server;
import org.hch.rpc.common.protocol.Message;
import org.hch.rpc.common.protocol.Response;

import java.util.concurrent.CompletableFuture;

/**
 * Created by chenghao on 9/19/16.
 */
public class ResponseHandler extends SimpleChannelInboundHandler<Message>{
    private Server server;

    public ResponseHandler(Server server){
        this.server = server;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        Response response=(Response)msg.getBody();
        if(server.getRequest(response.getRequestId())!=null){
            CompletableFuture future= server.getFuture(response.getRequestId());
            server.clearRequest(response.getRequestId());
            future.complete(response);
        }
    }
}
