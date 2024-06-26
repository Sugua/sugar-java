package com.suagr.study.spring;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @Description 读取不同资源
 *
 * @Author sugar
 * @Date 2020/8/20 3:24 PM
 * @Version 1.0
 */
public class ResourceTest {
    public static void main(String[] args) throws Exception {

        //读取内存资源 和ByteArrayInputStream类似
//        Resource source = new ByteArrayResource("helloWorld".getBytes());

//        Resource source = new FileSystemResource("/Users/sugar/sugar/asiainfo/JobContent/进入2019了.txt");

        Resource source = new ClassPathResource("applicationContext.xml");






        System.out.println("数据的长度：" + source.contentLength());
        //如果InputStream ,那么可以利用Scanner简化接受
        //getInputStream 是 InputStreamSource 的方法
        Scanner scanner = new Scanner(source.getInputStream());
        scanner.useDelimiter("/n");

        while (scanner.hasNext()) {

            System.out.println(scanner.next());
        }
        scanner.close();



    }

}
