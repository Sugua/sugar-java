package com.sugar.study.design.pattern.mementoPattern;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/3/1 10:33 AM
 * @Version 1.0
 */
public class Main {
     public static void main(String[] args){


         Originator originator = new Originator();

         originator.setState("ON");
         originator.show();

         //保存状态
         Caretaker caretaker = new Caretaker();
         caretaker.setMemento(originator.createMemento());


         originator.setState("OFF");
         originator.show();

         originator.setMemetoState(caretaker.getMemento());
         originator.show();


     }
}
