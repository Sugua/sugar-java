package com.sugar.study.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataInTest {
    public static void main(String[] args) {
        try(FileInputStream fileInputStream = new FileInputStream("input.txt");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
            int i = dataInputStream.readInt();
            System.out.println(dataInputStream.readBoolean());
            System.out.println(i);
            System.out.println(dataInputStream.readDouble());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
