package com.suagr.study.spring;

import com.sugar.study.spring.vo.ResourceBean;
import com.sugar.study.spring.vo.ResourceBeanList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/9/3 4:22 PM
 * @Version 1.0
 */
public class ResourceBeanTest {
    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ResourceBeanList rb = context.getBean("rbs", ResourceBeanList.class);
        List<Resource> resources = rb.getResources();
        Iterator<Resource> iterator = resources.iterator();
        while (iterator.hasNext()) {
            Resource resource = iterator.next();
            Scanner scanner = new Scanner(resource.getInputStream());
            scanner.useDelimiter("/n");

            while (scanner.hasNext()) {

                System.out.println(scanner.next());
            }
            scanner.close();
        }


    }
}
