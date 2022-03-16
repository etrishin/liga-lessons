package ru.digitalleague.ocs.liga.lessons.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class NIO2Samples {
    public static void main(String[] args) {
        String fileName = "sample.txt";

        try {
          //копирование
            Path res = Files.copy(Paths.get(fileName), Paths.get(fileName + ".copy"), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(res);
            System.out.println(res.toAbsolutePath());

          //читать все байты
            //byte[] bytes = Files.readAllBytes(Paths.get(fileName)); // OutOfMemoryError
            //System.out.println(Arrays.toString(bytes));

          //читать все строки
            //System.out.println(Files.readAllLines(Paths.get(fileName)));
            //System.out.println(Files.readAllLines(Paths.get("sample_cyr.txt")));
            //System.out.println(Files.readAllLines(Paths.get("sample_cyr_cp1251.txt"), Charset.forName("cp1251")));
            //System.out.println(Files.readAllLines(Paths.get("sample_cyr.txt"), Charset.forName("utf-8")));
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
