package com.sugar.study.design.pattern.mementoPattern;

/**
 * @Description 管理者
 * @Author sugar
 * @Date 2023/3/1 10:32 AM
 * @Version 1.0
 */
public class Caretaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public Caretaker setMemento(Memento memento) {
        this.memento = memento;
        return this;
    }
}
