package com.sugar.study.design.pattern.mementoPattern;

/**
 * @Description 发起人
 * @Author sugar
 * @Date 2023/3/1 10:28 AM
 * @Version 1.0
 */
public class Originator {

    private String state;
    private Memento memento;


    public String getState() {
        return state;
    }

    public Originator setState(String state) {
        this.state = state;
        return this;
    }


    public void setMemetoState(Memento memento) {
        this.state=memento.getState();
    }

    public Memento createMemento(){

        return new Memento(state);
    }



    public void show(){

        System.out.println("State=" + state);

    }
}
