package com.sugar.study.design.pattern.compositePattern;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/3/1 3:00 PM
 * @Version 1.0
 */
public class Main {

public static void main(String[] args) {
    //家谱
    Composite root = new Composite("陈吉元");
    root.add(new Leaf("陈帝书"));
    root.add(new Leaf("陈帝美"));
    Composite f = new Composite("陈帝中");
    Composite u = new Composite("陈帝禄");
    root.add(f);
    root.add(u);

    u.add(new Leaf("陈雪冬"));
    u.add(new Leaf("陈小丫"));
    Composite m = new Composite("陈小刚");
    f.add(m);
    m.add(new Leaf("陈非晚"));
    m.add(new Leaf("陈云开"));


    root.display(1);

    String str = "bacct_id bacct_id_{},sett_money sett_money_{},remark remark_{},";
    System.out.println(str.replace("{}","mz"));
    System.out.println(str.substring(0,str.length()-1));


}

}
