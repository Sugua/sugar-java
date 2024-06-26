package com.sugar.spider.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description
 *
 * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。

说明：解集不能包含重复的子集。

例如，给出 n = 3，生成结果为：

[
"((()))",
"(()())",
"(())()",
"()(())",
"()()()"
]
 * @Author sugar
 * @Date 2020/8/14 3:31 PM
 * @Version 1.0
 */
public class Parentheses {




    public static void main(String[] args){
        String s = "I am chenXiaogang,I from China ,I am fine,Thank you.";

        char[] chars = s.toCharArray();

        String i = s.substring(s.indexOf('I'));

        System.out.println(i);
//        String s1 = String.valueOf(chars);

//        char[] c2 = new char[chars.length];
//        System.out.println(s.indexOf('I'));
//
//        for (char c : s.toCharArray()) {
//        }
//        s.indexOf('p');
//
//        s.lastIndexOf('p');
//
//        Parentheses parentheses = new Parentheses();
//        System.out.println(parentheses.generateParenthesis(3));
    }

    /**
     *
     * @param n
     * @return
     */

    public List<String> generateParenthesis(int n) {
        Set<String> res = new HashSet<String>();
        if (n <= 0) {
            res.add("");
        } else {
            List<String> pre = generateParenthesis(n - 1);
            for (String str : pre) {
                for (int i = 0; i < str.length(); ++i) {
                    if (str.charAt(i) == '(') {
                        str = str.substring(0, i + 1) + "()" + str.substring(i + 1, str.length());
                        res.add(str);
                        str = str.substring(0, i + 1) +  str.substring(i + 3, str.length());
                    }
                }
                res.add("()" + str);
            }
        }
        return new ArrayList<String>(res);
    }


    public boolean CheckPermutation(String s1, String s2) {
        boolean flag=false;
        if (s1.length()!=s2.length()){
            return flag;
        }
        char[] c1 = s1.toCharArray();

        char[] c2 = new char[s2.length()];
        for (char c : s1.toCharArray()) {
            s2.indexOf(c);
//            s1
        }




        return flag;



    }
}
