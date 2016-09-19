package org.hch.rpc.client.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.hch.rpc.client.manage.Server;
import org.hch.rpc.common.protocol.Response;
import org.hch.rpc.common.protocol.codec.MessageDecoder;
import org.hch.rpc.common.protocol.codec.MessageEncoder;
import org.hch.rpc.common.protocol.marshalling.Marshalling;

/**
 * Created by chenghao on 9/8/16.
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private Marshalling marshalling;
    private Server server;
    public ClientChannelInitializer(Marshalling marshalling, Server server){
        this.marshalling=marshalling;
        this.server = server;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
            .addLast(new MessageDecoder(marshalling, Response.class))
            .addLast(new MessageEncoder(marshalling))
            .addLast(new ResponseHandler(server));

    }
}
