package com.sugar.study;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Description 校验括号是否是对的
 * @Author sugar
 * @Date 2022/5/18 4:04 PM
 * @Version 1.0
 */
public class stack {



    public static void main(String[] args){
        System.out.println(isValid("([000]{})"));




//利用栈后进先出的思想来检测

    }


    public static boolean isValid(String s){
        // 括号之间的对应规则
        HashMap<Character, Character> mappings = new HashMap<Character, Character>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (mappings.containsKey(chars[i])) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != mappings.get(chars[i])) {
                    return false;
                }
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }
}
