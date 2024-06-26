package com.sugar.study.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description 函数式 编程@FunctionalInterface 有FunctionalInterface 注解
 * 4大函数式接口 java.util.function
 * Function<T,R>
 *     T 表传入参数
 *     R 表返回参数
 * Consumer
 * Predicate
 * Supplier
 *
 * @Author sugar
 * @Date 2021/8/7 2:27 PM
 * @Version 1.0
 */
public class FunctionDemo {



    public static void main(String[] args) {


        Function<String ,String> function=str->{return str;};
        System.out.println(function.apply("ss"));
        //Predicate 断定型接口，有一个输入参数， 返回值boolean
        Predicate<String> predicate =str-> str.isEmpty();
        System.out.println(predicate.test(""));

        //Consumer 接口 只有一个输入参数， 没返回值
        Consumer<String> consumer=str-> System.out.println("str");
        consumer.accept("consumer");
        //Supplier 接口，供给型接口 没有入参， 只有返回值
        Supplier<String> supplier=()->{return "Supplier ";};
        System.out.println(supplier.get());


    }


}
