package com.sugar.study.lambda;

import java.util.function.Supplier;

/**
 * @Description Supplier 接口产生给定泛型类型的结果。 与 Function 接口不同，Supplier 接口不接受参数。
 * @Author sugar
 * @Date 2022/4/29 11:22 AM
 * @Version 1.0
 */
public class SupplierDemo {
    public static void main(String[] args){
        Supplier<String> suppliers = String::new;

        System.out.println(suppliers.get().length());
    }
}
