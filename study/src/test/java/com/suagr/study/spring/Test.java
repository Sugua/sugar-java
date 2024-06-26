package com.suagr.study.spring;

import com.sugar.study.spring.Apple;
import com.sugar.study.spring.Fruit;
import com.sugar.study.spring.service.AdminService;
import com.sugar.study.spring.vo.Company;
import com.sugar.study.spring.vo.Dept;
import com.sugar.study.spring.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/8/17 5:26 PM
 * @Version 1.0
 */

public class Test {
    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//
//        System.out.println("*****************");
//        Fruit fruit = context.getBean("apple", Apple.class);//指定名称，
//
//        fruit.eat();

        Emp emp = context.getBean("emp", Emp.class);


        Company company = context.getBean("company", Company.class);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        AdminService adminService = context.getBean("adminServiceImpl", AdminService.class);
//
//
        adminService.login();
//        Resource
//        InputStreamSource
//        System.out.println(emp);

        System.out.println(Integer.SIZE-3);

    }
}
