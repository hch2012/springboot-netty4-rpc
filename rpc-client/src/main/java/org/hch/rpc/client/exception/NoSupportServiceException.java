package org.hch.rpc.client.exception;

import org.hch.rpc.common.exception.RpcException;

/**
 * Created by chenghao on 9/21/16.
 */
public class NoSupportServiceException extends RpcException{
    public NoSupportServiceException(String type){
        super("No support service for "+type);
    }
}
