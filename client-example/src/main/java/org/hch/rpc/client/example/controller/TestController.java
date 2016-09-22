package org.hch.rpc.client.example.controller;

import org.hch.rpc.server.api.example.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenghao on 9/22/16.
 */
@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return helloService.hello();
    }

}
