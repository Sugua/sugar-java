package com.sugar.study.spring.vo;

import java.util.*;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/8/18 10:37 AM
 * @Version 1.0
 */
public class Company {
    private String msg[];
    private Integer data[];

    private List<String> msgs;
    private Set<String> set;

    private Map<Integer, String> map;

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public Company setProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public Company setMap(Map<Integer, String> map) {
        this.map = map;
        return this;
    }

    public Set<String> getSet() {
        return set;
    }

    public Company setSet(Set<String> set) {
        this.set = set;
        return this;
    }

    public List<String> getMsgs() {
        return msgs;
    }

    public Company setMsgs(List<String> msgs) {
        this.msgs = msgs;
        return this;
    }

    public String[] getMsg() {
        return msg;
    }

    public Company setMsg(String[] msg) {
        this.msg = msg;
        return this;
    }

    public Integer[] getData() {
        return data;
    }

    public Company setData(Integer[] data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "msg = " + Arrays.toString(msg) + " ,data = " + Arrays.toString(data) + " ,msgs = " + msgs
                + ",set = " + set + " ，map = " + map + ",pro=" + properties;

    }
}
