package org.hch.rpc.server.api.example.service;

import org.hch.rpc.common.annotation.RpcServiceApi;

/**
 * Created by chenghao on 9/8/16.
 */
@RpcServiceApi(type = "hello")
public interface HelloService {
    String hello();
}
