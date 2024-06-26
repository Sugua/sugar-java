package com.sugar.study.csdn;

/**
 * @Description todo
 * @Author sugar
 * @Date 2022/10/18 9:03 PM
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str_0 = scan.nextLine().trim();
        int n = Integer.parseInt(str_0);


        String str_1 = scan.nextLine();
        String[] line_list_1 = str_1.trim().split(" ");
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < line_list_1.length; i++) {
            arr.add(Integer.parseInt(line_list_1[i]));
        }


        scan.close();

        ArrayList<Integer> result = solution(n, arr);


        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }


    }

    public static ArrayList<Integer> solution(int n, ArrayList<Integer> arr) {
        ArrayList<Integer> result = new ArrayList<>();

        arr.stream().filter(i -> i.intValue() % 2 != 0).forEach(i -> result.add(i));
        arr.stream().filter(i -> i.intValue() % 2 == 0).forEach(i -> result.add(i));

        return result;
    }
}
