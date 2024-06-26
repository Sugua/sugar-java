package com.sugar.study.spring.vo;

import org.springframework.core.io.Resource;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/9/3 4:14 PM
 * @Version 1.0
 */
public class ResourceBean {

    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public ResourceBean setResource(Resource resource) {
        this.resource = resource;
        return this;
    }
}
