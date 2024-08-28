package com.sugar.study.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderTest {
    public static void main(String[] args) {
        try(FileReader fileReader=new FileReader("input.txt")){
            int content;
//            long skip=fileReader.skip(3);
            while ((content=fileReader.read())!=-1){
                System.out.print((char)content);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
