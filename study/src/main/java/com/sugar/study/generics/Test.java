package com.sugar.study.generics;

import java.util.List;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/7/20 10:42 PM
 * @Version 1.0
 */
public class Test {

    public void testExtends(List<? extends Fruit> list) {
//        list.add(new Apple());
//        list.add(new Fuit());
        Fruit fruit = list.get(1);
    }

    public void testSuper(List<? super Fruit> list){
        list.add(new Apple());
//        list.add(new Food());
        list.add(new Fruit());
    }
}

class Food {
}

class Fruit extends Food {
}

class Apple extends Fruit {
}

class Banana extends Fruit {
}
