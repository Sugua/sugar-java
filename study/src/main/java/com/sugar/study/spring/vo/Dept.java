package com.sugar.study.spring.vo;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/8/18 9:42 AM
 * @Version 1.0
 */
public class Dept {

    private Integer deptNo;
    private String name;

    public Dept(Integer deptNo, String name) {

        this.deptNo = deptNo;
        this.name = name;
    }


    public  Dept(){
    }


    public Integer getDeptNo() {
        return deptNo;
    }

    public Dept setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dept setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "部门编码： " + deptNo + "，部门名称：" + name;
    }
}
