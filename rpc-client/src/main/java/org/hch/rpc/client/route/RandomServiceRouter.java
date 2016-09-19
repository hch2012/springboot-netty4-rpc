package org.hch.rpc.client.route;

import org.hch.rpc.client.manage.Server;
import org.hch.rpc.common.utils.RandomUtils;

import java.util.List;

/**
 * Created by chenghao on 9/9/16.
 */
public class RandomServiceRouter implements ServiceRouter{
    @Override
    public Server route(List<Server> list) {
        int random= RandomUtils.randomInt(0,list.size());
        return list.get(random);
    }
}
