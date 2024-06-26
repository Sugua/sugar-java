package com.sugar.study.lambda;

import java.util.function.Function;

/**
 * @Description Function 接口接受一个参数并生成结果。默认方法可用于将多个函数链接在一起（compose, andThen）：
 * @Author sugar
 * @Date 2022/4/29 11:17 AM
 * @Version 1.0
 */
public class FunctionDemo {

    public static void main(String[] args){
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> stringStringFunction = toInteger.andThen(String::valueOf);
        System.out.println(toInteger.apply("123").getClass());
        System.out.println(stringStringFunction.apply("123").getClass());


    }
}
