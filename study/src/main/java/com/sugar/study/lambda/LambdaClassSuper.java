package com.sugar.study.lambda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @Description todo
 * @Author sugar
 * @Date 2022/4/25 4:24 PM
 * @Version 1.0
 */
public class LambdaClassSuper {

    //    LambdaInterface sf(){
//        return null;
//    }
    public static void main(String[] args) {
        Foo foo1 = new Foo(1l, "202201");
        Foo foo2 = new Foo(1l, "202202");
        Foo foo3 = new Foo(1l, "202203");
        Foo foo4 = new Foo(2l, "202203");
        Foo foo5 = new Foo(2l, "202205");

        List<Foo> list = new ArrayList<>();
        list.add(foo1);
        list.add(foo2);
        list.add(foo3);
        list.add(foo4);
        list.add(foo5);

        list.sort((f1,f2)-> f2.getMonth().compareTo(f1.getMonth()) );
        ArrayList<Foo> collect = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Foo::getId))), ArrayList::new));

        System.out.println(collect);


        LocalDate date = LocalDate.now();
        System.out.println(String.format("date format : %s", date));
        System.out.println(date);
    }


}

class Foo {
    Long id;
    String month;

    public Foo(Long id, String month) {
        this.id = id;
        this.month = month;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return id + ":" + month;
    }
}

