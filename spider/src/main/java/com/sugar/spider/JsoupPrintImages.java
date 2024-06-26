package com.sugar.spider;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2019/6/24 4:53 PM
 * @Version 1.0
 */
public class JsoupPrintImages {

    public static void main( String[] args ) throws IOException {
//        Jsoup.connect("").header("Content-Type", "application/json;charset=UTF-8")
        Connection.Response res = Jsoup.connect("http://10.182.34.20:8094/audit-auth/audit/usr/login")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")

                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();
//                .get();
        String token = res.cookie("token");
        System.out.println(res.body());

//        Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
//        Elements images = null;
//
//        for (Element image : images) {
//            System.out.println("src : " + image.attr("src"));
//            System.out.println("height : " + image.attr("height"));
//            System.out.println("width : " + image.attr("width"));
//            System.out.println("alt : " + image.attr("alt"));
//        }

    }


}
