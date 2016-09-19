package org.hch.rpc.common.protocol.marshalling.impl;

import com.alibaba.fastjson.JSON;
import org.hch.rpc.common.protocol.marshalling.Marshalling;

/**
 * Created by chenghao on 9/14/16.
 */
public class JsonMarshalling implements Marshalling{

    @Override
    public <T> T decode(byte[] code, Class<T> clazz) {
        return JSON.parseObject(new String(code),clazz);
    }

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONString(obj).getBytes();
    }
}
