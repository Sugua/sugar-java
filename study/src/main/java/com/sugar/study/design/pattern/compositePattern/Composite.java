package com.sugar.study.design.pattern.compositePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/3/1 2:47 PM
 * @Version 1.0
 */
public class Composite extends Component {
    private List<Component> children = new ArrayList<>();
    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component c) {
        children.add(c);

    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public void display(int depth) {
       super.display(depth);

        children.forEach(child -> child.display(depth + 2));

    }

}
