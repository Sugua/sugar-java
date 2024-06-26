package com.sugar.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Description 爬直播吧全部赛事
 * @Author sugar
 * @Date 2019/6/24 5:06 PM
 * @Version 1.0
 */
public class Test {


    public static void main(String[] args){
//        Set<User> set = new HashSet();
//        User user = new User("jack","123");
//        User user1 = new User("jack","123");
//        User user2 = user;
//        set.add(user);
//        set.add(user1);
//        set.add(user2);
//        System.out.println(set.size());
//        System.out.println("hash");
//        System.out.println(user.hashCode());
//        System.out.println(user1.hashCode());
//
//
//        System.out.println("hash");
//
//        String str = new String("ab");
//        String str1 = new String("ab");
//
//        Set<String> stringSet = new HashSet();
//
//        stringSet.add(str);
//        stringSet.add(str1);
//        System.out.println(stringSet.size());
//
//        System.out.println(str.hashCode());
//        System.out.println(str1.hashCode());

//        System.out.println("f(2) = "+f(2));


//        Scanner s = new Scanner(System.in);
//        String s1 = s.next();
//        System.out.println(s1);
//        s.close();
//
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String s2 = reader.readLine();
//            System.out.println(s2);
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        System.out.println(list);
        list.add(1, 2);
//        ArrayList.binarySearch()

        System.out.println(list);
        List<Integer> list1 = new LinkedList<Integer>();

        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);

//        Vector



    }

    public  static int f( int v){
        try {
            return v * v;
        }finally {
            return 0;
        }

    }

    private static void event() {
        String url = "https://www.zhibo8.cc/index2.htm";
        String charset = "utf-8";

        String html = HttpClient.get(url, charset);

        Document doc = Jsoup.parse(html);


        Elements boxs = doc.select(".box");

        for (Element box : boxs) {
            Element titlebar = box.select(".titlebar h2").get(0);
            System.out.println(titlebar.text());
            Elements lis = box.select(".content li");

            for (Element li : lis) {
                Elements a = li.select("a");
                a.remove();
                System.out.println(li.text());
            }
        }
    }

    private static Map<String, String> news() {
        Map<String,String> map = new HashMap<String, String>();
        String url = "https://news.zhibo8.cc/zuqiu/";
        String charset = "utf-8";

        String html = HttpClient.get(url, charset);

        Document doc = Jsoup.parse(html);
        //首先获取顶部新闻
        Elements tops = doc.select(".topleftbox a");
        System.out.println(tops.size());

        for (Element top : tops) {
            String title = top.text();
            String href = "https:" + top.attr("href");
            map.put(title, href);
        }
        Elements m_left = doc.select(".m_left");
        Elements lis = m_left.select("li a");
        System.out.println(lis.size());
        for (Element l : lis) {
            String title = l.text();
            String href = "https:" + l.attr("href");
            if (map.containsKey(title)) {
                continue;
            }
            map.put(title, href);
        }
        return map;

    }
}
