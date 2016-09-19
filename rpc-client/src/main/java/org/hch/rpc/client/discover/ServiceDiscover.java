package org.hch.rpc.client.discover;

import org.hch.rpc.client.manage.Handler;

import java.util.Map;

/**
 * Created by chenghao on 9/8/16.
 */
public interface ServiceDiscover {
    /**
     *
     * @return  Map&lt;serverType,Map&lt;serverName,serverHost&gt;&gt;
     */
    Map<String,Map<String,String>> discover();
    void watchService();
    ServiceDiscover bindAddHandler(Handler handler);
    ServiceDiscover bindRemoveHandler(Handler handler);
}
