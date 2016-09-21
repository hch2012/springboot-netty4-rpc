package org.hch.rpc.server.api.example.service;

import org.hch.rpc.common.annotation.RpcServiceApiDescription;

/**
 * Created by chenghao on 9/8/16.
 */
@RpcServiceApiDescription(group = "test")
public interface TestService {
    String test();
}
