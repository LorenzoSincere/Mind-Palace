package com.lorenzo.mind_palace.controller;

import com.lorenzo.mind_palace.entity.Test;
import com.lorenzo.mind_palace.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/13 21:04
 **/
@RestController
public class TestController {

    // @Value("${test.hello}")
    // private String testHello;
    // @RequestMapping("/hello")
    // @RequestMapping(value = "/user/1", method = RequestMethod.GET)
    // @GetMapping("/hello")
    // public String hello() {
    //    return "hello, world" + testHello;
    // }

    @Resource
    private TestService testService;

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post, " + name;
    }

    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }
}
