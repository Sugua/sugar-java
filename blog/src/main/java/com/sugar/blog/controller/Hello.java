package com.sugar.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description test
 * @Author sugar
 * @Date 2019/6/26 11:41 AM
 * @Version 1.0
 */
@Controller
@RequestMapping("hello/v1/")
public class Hello {
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);
    @Autowired
    private Environment environment;

    @Value("${server.port}")
    private String port;

    @GetMapping("hello")
    public String hello(ModelMap map) {

        map.put("message", "hello ,Sugar");
        return "index";
    }

}
