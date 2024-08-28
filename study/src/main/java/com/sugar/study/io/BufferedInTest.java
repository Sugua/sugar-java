package com.sugar.study.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BufferedInTest {

    public static void main(String[] args) {

        try(FileInputStream fileInputStream = new FileInputStream("input.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);) {
            byte[] bytes = new byte[10];
            while (bufferedInputStream.read(bytes)!=-1){
                System.out.print(new String(bytes, StandardCharsets.UTF_8));

            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
