package com.sugar.study.lambda;

import java.util.function.Predicate;

/**
 @FunctionalInterface
 public interface Predicate<T> {

 // 该方法是接受一个传入类型,返回一个布尔值.此方法应用于判断.
 boolean test(T t);

 //and方法与关系型运算符"&&"相似，两边都成立才返回true
 default Predicate<T> and(Predicate<? super T> other) {
 Objects.requireNonNull(other);
 return (t) -> test(t) && other.test(t);
 }
 // 与关系运算符"!"相似，对判断进行取反
 default Predicate<T> negate() {
 return (t) -> !test(t);
 }
 //or方法与关系型运算符"||"相似，两边只要有一个成立就返回true
 default Predicate<T> or(Predicate<? super T> other) {
 Objects.requireNonNull(other);
 return (t) -> test(t) || other.test(t);
 }
 // 该方法接收一个Object对象,返回一个Predicate类型.此方法用于判断第一个test的方法与第二个test方法相同(equal).
 static <T> Predicate<T> isEqual(Object targetRef) {
 return (null == targetRef)
 ? Objects::isNull
 : object -> targetRef.equals(object);
 }
 }
 * @Author sugar
 * @Date 2022/4/29 10:53 AM
 * @Version 1.0
 */
public class PredictDemo {

    public static void main(String[] args){
        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("foo"));
        System.out.println(predicate.negate().test("foo"));




    }

    class Person{
        String name;
        int age;
         



    }
}

