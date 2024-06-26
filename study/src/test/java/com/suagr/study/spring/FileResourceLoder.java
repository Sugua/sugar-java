package com.suagr.study.spring;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Scanner;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/9/3 3:21 PM
 * @Version 1.0
 */
public class FileResourceLoder {


    public static void main(String[] args) throws Exception{
        ResourceLoader loader = new DefaultResourceLoader();
//        Resource source = loader.getResource("classpath:applicationContext.xml");
//        Resource source = loader.getResource("file:/Users/sugar/sugar/asiainfo/JobContent/进入2019了.txt");
        Resource source = loader.getResource("https://yishoushi.cn/");


        //读取内存资源 和ByteArrayInputStream类似
//        Resource source = new ByteArrayResource("helloWorld".getBytes());

//        Resource source = new FileSystemResource("/Users/sugar/sugar/asiainfo/JobContent/进入2019了.txt");

//        Resource source = new ClassPathResource("applicationContext.xml");






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
