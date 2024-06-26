package com.sugar.study.reflector;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 测试
 * @Author sugar
 * @Date 2021/8/28 3:06 PM
 * @Version 1.0
 */
public class Test {
    private static final Map<String, Field> valFields = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        Val val = new Val();

        val.setVar1("中文1");
        val.setVar2("中文2");
        val.setVar3("中文3");
        val.setVar1Id("11");
        val.setVar2Id("22");
        val.setVar3Id("33");

        Class valClass = val.getClass();

        if (valFields.isEmpty()) {

            Field[] fields = valClass.getDeclaredFields();
//            System.out.println(fields.length);
            for (Field field : fields) {
                String name = field.getName();
//                field.setAccessible(true);
//                System.out.println(name);
                System.out.println(name + "->" + (StringUtils.contains(name, "var") && !StringUtils.contains(name, "Id")));

                if (StringUtils.contains(name, "var") && (!StringUtils.contains(name, "Id"))) {
                    valFields.put(name, field);
                }
            }

        }

        valFields.forEach((k, f) -> {
            try {
                f.setAccessible(true);
                Field field = valClass.getDeclaredField(k + "Id");
                field.setAccessible(true);
                Object o = field.get(val);
                f.set(val, o);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
//                throw new RewardException("复制产生了失败：没有这个字段");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
//                throw new RewardException("复制产生了失败：非法进入");

            }
        });


//        System.out.println(valFields);
        System.out.println(val.getVar1());
        System.out.println(val.getVar2());
        System.out.println(val.getVar3());
        System.out.println(val.getVar1Id());
        System.out.println(val.getVar2Id());
        System.out.println(val.getVar3Id());

    }

}
