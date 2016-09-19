package org.hch.rpc.client.route;

import org.hch.rpc.client.manage.Server;

import java.util.List;

/**
 * Created by chenghao on 9/9/16.
 */
public interface ServiceRouter {
    Server route(List<Server> list);
}
