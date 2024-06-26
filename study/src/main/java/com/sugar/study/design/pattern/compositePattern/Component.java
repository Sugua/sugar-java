package com.sugar.study.design.pattern.compositePattern;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/3/1 11:30 AM
 * @Version 1.0
 */
public abstract class Component {

    public String name;


    public Component(String name) {
        this.name = name;
    }

    public abstract  void add(Component c);
    public abstract  void remove(Component c);
    public  void display(int depth){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append('-');
        }
        System.out.println(sb.append(name));

    }

}
