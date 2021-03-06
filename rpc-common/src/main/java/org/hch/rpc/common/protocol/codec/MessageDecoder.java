package org.hch.rpc.common.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.hch.rpc.common.protocol.Header;
import org.hch.rpc.common.protocol.Message;
import org.hch.rpc.common.protocol.marshalling.Marshalling;

/**
 * Created by chenghao on 9/14/16.
 */
public class MessageDecoder extends LengthFieldBasedFrameDecoder{
    private Marshalling marshalling;
    private Class bodyType;
    public MessageDecoder(Marshalling marshalling,Class bodyType) {
        super(65536, 4, 4);
        this.marshalling=marshalling;
        this.bodyType=bodyType;
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        in=(ByteBuf) super.decode(ctx, in);
        if(in==null)
            return null;
        Header header=new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        byte[] buf=new byte[in.readableBytes()];
        in.readBytes(buf);
        Object object=marshalling.decode(buf,bodyType);
        Message message=new Message(header,object);
        return message;
    }
}
