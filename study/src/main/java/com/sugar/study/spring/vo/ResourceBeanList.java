package com.sugar.study.spring.vo;

import org.springframework.core.io.Resource;

import java.util.List;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/9/3 4:14 PM
 * @Version 1.0
 */
public class ResourceBeanList {

    private List<Resource> resources;

    public List<Resource> getResources() {
        return resources;
    }

    public ResourceBeanList setResources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }
}
