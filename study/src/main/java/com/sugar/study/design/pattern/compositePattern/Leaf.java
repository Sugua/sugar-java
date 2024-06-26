package com.sugar.study.design.pattern.compositePattern;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/3/1 2:45 PM
 * @Version 1.0
 */
public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component c) {
        System.out.println("can not add");
    }

    @Override
    public void remove(Component c) {
        System.out.println("can not remove");
    }

    @Override
    public void display(int depth) {
        super.display(depth);
    }
}
