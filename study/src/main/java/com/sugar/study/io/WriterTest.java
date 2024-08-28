package com.sugar.study.io;

import java.io.FileWriter;
import java.io.IOException;

public class WriterTest {
    public static void main(String[] args) {
        try(FileWriter writer = new FileWriter("output.txt")) {
            writer.write("sugar要好好学习哈~");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
