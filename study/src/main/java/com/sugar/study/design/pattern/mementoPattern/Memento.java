package com.sugar.study.design.pattern.mementoPattern;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/3/1 10:25 AM
 * @Version 1.0
 */
public class Memento {
    private String state;

    public String getState() {
        return state;
    }

    public Memento setState(String state) {
        this.state = state;
        return this;
    }

    public Memento(String state) {
        this.state = state;
    }

}
