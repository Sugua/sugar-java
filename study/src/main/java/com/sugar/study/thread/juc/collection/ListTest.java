package com.sugar.study.thread.juc.collection;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 集合
 * @Author sugar
 * @Date 2021/8/4 5:17 PM
 * @Version 1.0
 */
public class ListTest {
    public static void main(String[] args) {
        /**
         *  并发异常
         *  List<String> list = new ArrayList<>();oncurrentModificationException
         *
         *  解决方案
         *  1. List<String> list = new Vector<>();
         *  2. List<String> list = Collections.synchronizedList(new ArrayList<>());
         *  3. List<String> list = new CopyOnWriteArrayList<>();
         */
        List<Integer> users = Arrays.asList(1,2, 3, 4, 5);


        CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>(users);
        System.out.println(integers);
        for (int i = 0; i < 10; i++) {

            integers.forEach(l -> {

                if (l % 2 == 0) {
                    integers.remove(l);

                    integers.add((int) Math.ceil(l + Math.random() * 10 ));
                    System.out.println(l);
                }
            });
            System.out.println(integers);

        }
        System.out.println("--------------");

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
                list.add(""+i);
//            }, String.valueOf(i)).start();
        }

        list.stream().forEach(l->{
            if(StringUtils.equals(l,"1")) return;
            System.out.println(l);
        });

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String s = "202201";

        System.out.println(sdf.format(date).equals(s));


//
//        System.out.println(String.valueOf("str,sa"));
//        AtomicInteger index = new AtomicInteger(1);
//        index.getAndIncrement();
//        System.out.println(index.get());
//        String s = "strsa";
//
//        String[] split = s.split(",");
//        Arrays.stream(split).forEach(s1 -> System.out.println(s1));


//        String str = "15:00";
//        String[] sp = str.split(":");
//

//        LocalDateTime now = LocalDateTime.now();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH, 11);
//        Integer value = Integer.valueOf(sp[0]);
//        calendar.set(Calendar.HOUR, value);
//        calendar.set(Calendar.MINUTE, Integer.valueOf(sp[1]));
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(calendar.getTime()));
//
//        System.out.println(ZoneId.systemDefault());
//
//        LocalDateTime localDateTime = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//        System.out.println(value);
//        System.out.println(now);
//        System.out.println(localDateTime);
//        System.out.println(now.isAfter(localDateTime));
//
//
//        LocalDate now1 = LocalDate.now();
//        System.out.println(now1);
//        int year = now1.getYear();
//        Month month = now1.getMonth();
//        int monthValue = now1.getMonthValue();
//        System.out.println(year +"  "+ monthValue +"  "+now1.getDayOfYear() );
//
//        LocalDate now2 = LocalDate.now();
//        System.out.println(now2);
//        System.out.println(LocalDate.now());
//        System.out.println(LocalDate.now());



//        Set<Integer> set = new ConcurrentSkipListSet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//        System.out.println(set.contains(1));
//        System.out.println(set.contains(4));
//        System.out.println(!set.contains(4));
//        StringBuilder sb = new StringBuilder();
//        sb.append("FEE !=null or FEE !=null or ");
//        System.out.println(sb.toString().substring(0, sb.length()-3));
//
//
//
//
//        String tableName = "RPT_${EPARCHY_CODE}_${eparchy_code}";
//        String code = "${EPARCHY_CODE}";
//        System.out.println(tableName.replaceAll("(?i)" + code, "0771"));

    }
}