package com.sugar.study.io;

import java.io.*;

public class ObjectIOTest {

    public static void main(String[] args) {
        IOBean sugar = IOBean.builder().name("sugar").age(32).sayHello("hello everyone!").build();
        System.out.println(sugar);
        writeObject(sugar);
        IOBean ioBean = readObject();
        System.out.println(ioBean);


    }

    private static void writeObject(IOBean sugar) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("ioBean.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(sugar);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static IOBean readObject() {
        try (
                FileInputStream fileInputStream = new FileInputStream("ioBean.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            IOBean bean = (IOBean) objectInputStream.readObject();
            return bean;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    }
