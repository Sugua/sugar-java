package com.sugar.study.thread.juc.collection;

import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Description todo
 * @Author sugar
 * @Date 2021/12/2 3:19 PM
 * @Version 1.0
 */
public class MapTest {
    private static volatile ConcurrentHashMap<String, Integer> keyTime = new ConcurrentHashMap();


    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getMonthValue());
        LocalDate localDate1 = localDate.minusMonths(1);
        System.out.println(localDate1.getMonthValue());
        System.out.println(localDate1.getYear());
        System.out.println(localDate1.getYear()+""+localDate1.getMonthValue());

        String str = "(city_code = #{ew.paramNameValuePairs.MPGENVAL1} AND eparchy_code = #{ew.paramNameValuePairs.MPGENVAL2} AND sett_month = #{ew.paramNameValuePairs.MPGENVAL3})";
        int index = str.indexOf("sett_month");

        String[] split = str.split("sett_month");
        System.out.println(split.length);
        System.out.println(split[1].indexOf("MPGENVAL"));

        String s = split[1];
        System.out.println(s.substring(s.indexOf("MPGENVAL"), s.indexOf("MPGENVAL") + 9));

    }
}
