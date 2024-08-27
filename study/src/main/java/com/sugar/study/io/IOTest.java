package com.sugar.study.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class IOTest {
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("input.txt")) {
            System.out.println("Number of remaining bytes:" + is.available());
            int content;
//            long skip=is.skip(2);
//            System.out.println("The actual number of bytes skipped:"+skip);
            System.out.println("The content read from file:");
//            while ((content = is.read()) != -1) {
//                System.out.print((char) content);
//            }


            byte[] bytes = new byte[10];

            while (is.read(bytes)!=-1){
                System.out.print(new String(bytes, StandardCharsets.UTF_8));
            }




        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
