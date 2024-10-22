package com.sugar.study.test;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        strContains();
        String exp = "if(${1012.在网月数}==${1022.在网天数}) return ture";
        List<String> list=new ArrayList<>();
        Map<Integer, Integer> map = indexItem(exp + "e");
        System.out.println(map);
        map.forEach((i,k)->list.add(exp.substring(i, k+1))
        );
        System.out.println(list);
        String join = StringUtils.join(list, ",");
        System.out.println(join);


    }

    private static Map<Integer,Integer> indexItem(String expExtension) {
        Map<Integer,Integer> map=new HashMap<>();
        if (expExtension.contains("${") && expExtension.contains("}")){
            char[] chars = expExtension.toCharArray();
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i]=='$'&& chars[i+1]=='{'){
                    stack.push(i);
                }
                if (chars[i]=='}'&&!stack.isEmpty()){
                    map.put(stack.pop(),i);
                }
            }
        }
        return map;
    }

    private static void strContains() {
        String filename="我方开具CN_DN统计表-202405_一卡多号.xlsx";
        String filenamePrefix="一卡多号我方开具CN_DN统计表";
        System.out.println(filename.contains(filenamePrefix));

        List<Integer> list=new ArrayList();
        list.add(1);
        System.out.println(CollectionUtils.isEmpty(list));
    }
}
