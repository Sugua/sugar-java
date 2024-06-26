package com.sugar.study.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 流化编程
 * @Author sugar
 * @Date 2021/8/7 2:49 PM
 * @Version 1.0
 */
public class StreamDemo {

    public static void main(String[] args) {
        User user3 = new User(3, "陈小刚", 28);
        User user2 = new User(2, "陈秦", 2);

        User user1 = new User(1, "秦秀", 18);
        User user4 = new User(4, "陈帝中", 48);
        User user5 = new User(1, "罗昭分", 18);

        List<User> users = Arrays.asList(user2,user1, user3, user4, user5);

        List<Integer> collect = users.stream().map(user -> user.getId()).collect(Collectors.toList());
        Map<Integer, User> collect2 = users.stream().collect(Collectors.toMap(User::getId, User -> User));



        List<User> collect1 = users.stream()
                .filter(u -> u.getAge() == 18)
//                .filter(u -> u.getId() % 2 == 0)
                .sorted((u1, u2) -> {
                    int x = u1.getId();
                    int y = u2.getId();
//                    u1.setName(u1.getName()+"~");
                    return x < y ? -1 : (x == y ? 0 : 1);
                }).collect(Collectors.toList());
        System.out.println(users);
        List<User> us = users.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(User::getAge))), ArrayList::new));


        System.out.println(us);

        String s = null;
        for (int i = 0; i < 12; i++) {
            s = s==null?" ":s + " " + i;
        }
        System.out.println(s);
        System.out.println(s.trim().replace(" ",","));

    }
}


class User {
    private Integer id;
    private String name;
    private Integer age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return id + "\t" + name;
    }
//
//    @Override
//    public int compareTo(User o) {
//        int x = this.getId();
//        int y = o.getId();
//        return x < y ? -1 : (x == y ? 0 : 1);
////        return this.id.compareTo(o.getId());
//
//    }
}