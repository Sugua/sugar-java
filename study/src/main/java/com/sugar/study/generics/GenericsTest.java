package com.sugar.study.generics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 泛型
 * @Author sugar
 * @Date 2022/3/23 4:41 PM
 * @Version 1.0
 */
public class GenericsTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(12);
//        list.add("a");//报错

        Class<? extends List> clazz = list.getClass();


        try {
            Method add = clazz.getDeclaredMethod("add", Object.class);
            //java用的伪泛型，只在编译的时候检测非法类型， 在运行的泛型信息会被擦除掉，通常说的是泛型擦除
            add.invoke(list, "a");
            System.out.println(list);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
