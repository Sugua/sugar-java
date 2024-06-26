package com.sugar.study.spring.vo;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/8/18 10:06 AM
 * @Version 1.0
 */
public class Emp {
    private Integer empNo;
    private String name;
    private Dept dept;
    private boolean close;

    public boolean isClose() {
        return close;
    }

    public Emp setClose(boolean close) {
        this.close = close;
        return this;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public Emp setEmpNo(Integer empNo) {
        this.empNo = empNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public Emp setName(String name) {
        this.name = name;
        return this;
    }

    public Dept getDept() {
        return dept;
    }

    public Emp setDept(Dept dept) {
        this.dept = dept;
        return this;
    }

    @Override
    public String toString() {
        return "工号：" + empNo + ",姓名：" + name + ",是否关闭：" + close + ",所在部门消息：" + dept;
    }
}
