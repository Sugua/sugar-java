package com.sugar.study.unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description Class操作
 * @Author sugar
 * @Date 2024/6/25 10:39 PM
 * @Version 1.0
 */
@Data
public class User {

    public static String name = "sugar";
    int age;

    public static void main(String[] args) throws NoSuchFieldException {
        User user = new User();
        Unsafe unsafe = UnsafeMain.reflectGetUnsafe();

        System.out.println(unsafe.shouldBeInitialized(User.class));//判断是否是需要static属性初始化
        Field nameField = User.class.getDeclaredField("name");
        long fieldOffset = unsafe.staticFieldOffset(nameField);//获取静态属性的偏移量
        Object fieldBase = unsafe.staticFieldBase(nameField);//获取静态属性的对象指针
        Object object = unsafe.getObject(fieldBase, fieldOffset);//在对象的的指定偏移量获取一个对象引用
        System.out.println(object);

    }
}
