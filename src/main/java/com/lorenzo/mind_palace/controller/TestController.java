package com.lorenzo.mind_palace.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author lorenzo
 * @date 2022/03/13 21:04
 **/
@RestController
public class TestController {

    //@Value("${test.hello}")
    private String testHello;
    // @RequestMapping("/hello")
    // @RequestMapping(value = "/user/1", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello() {
        return "hello, world" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post, " + name;
    }

    @GetMapping("/hello/get")
    public String helloGet(String name) {
        return "Hello World! Get, " + name;
    }
}
