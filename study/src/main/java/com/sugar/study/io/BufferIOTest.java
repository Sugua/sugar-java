package com.sugar.study.io;

import java.io.*;

public class BufferIOTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        buffer();//443~510
//        no_buffer();//224201
//        buffer_byte_array();//91
        no_buffer_byte_array();//155
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void buffer() {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\sugar\\job\\cmi\\doc\\registry-1.1.9-win64\\csfRegistry.jar");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream("C:\\sugar\\job\\cmi\\doc\\registry-1.1.9-win64\\csfRegistry.jar_bk");
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

            int content;
            while ((content = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(content);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void no_buffer() {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\sugar\\job\\cmi\\doc\\registry-1.1.9-win64\\csfRegistry.jar");
             FileOutputStream fileOutputStream = new FileOutputStream("C:\\sugar\\job\\cmi\\doc\\registry-1.1.9-win64\\csfRegistry.jar_bk")) {

            int content;
            while ((content = fileInputStream.read()) != -1) {
                fileOutputStream.write(content);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void buffer_byte_array() {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\sugar\\job\\cmi\\doc\\registry-1.1.9-win64\\csfRegistry.jar");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream("C:\\sugar\\job\\cmi\\doc\\registry-1.1.9-win64\\csfRegistry.jar_bk");
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

            int len;
            byte[] bytes = new byte[1024 * 4];
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void no_buffer_byte_array() {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\sugar\\job\\cmi\\doc\\registry-1.1.9-win64\\csfRegistry.jar");
             FileOutputStream fileOutputStream = new FileOutputStream("C:\\sugar\\job\\cmi\\doc\\registry-1.1.9-win64\\csfRegistry.jar_bk")) {

            int len;
            byte[] bytes = new byte[1024 * 4];
            while ((len = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
