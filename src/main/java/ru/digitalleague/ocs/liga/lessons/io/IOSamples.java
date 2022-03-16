package ru.digitalleague.ocs.liga.lessons.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class IOSamples {
    public static void main(String[] args) {
        String fileName = "sample.txt";
        //String fileName = "sample_cyr.txt";
        //String fileName = "sample_cyr_cp1251.txt";

        //readFileBeforeJava7(fileName);
        //readFile(fileName);

        //writeAllBytes();
        //readAllBytes();

        //raf(fileName);
        //rafWrite(fileName);

        //inputStreamReader(fileName); // + sample_cyr.txt
        //copy(fileName);
        //byteArrayOutputStream();

        //dataOutputStream();
        //serialization();
    }

    private static void readFileBeforeJava7(String fileName) {
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            readBytes(is);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    private static void readFile(String fileName) {
        try (InputStream is = new FileInputStream(fileName)) {
            //readBytes(is);
            //readSingle(is);
            //readSkip(is);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private static void readBytes(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        int readed = is.read(bytes);
        if (readed == -1) {
            System.out.println("readed[" + readed + "]: no data");
        } else {
            String str = new String(bytes, 0, readed);
            //String str = new String(bytes);
            System.out.println("readed[" + readed + "]: " + str + "[" + str.length() + "]");
        }
    }

    private static void readSingle(InputStream is) throws IOException {
        for (int i = 0; i < 3; i++) {
        //for (int i = 0; i < 18; i++) {
            int b = is.read();
            System.out.println("i = " + i + "; [" + b + "]: " + (char)b);
        }
    }

    private static void readSkip(InputStream is) throws IOException {
        long skipped = is.skip(6);
        //long skipped = is.skip(15);
        //long skipped = is.skip(20000);
        System.out.println("skipped: " + skipped);
        System.out.println((char)is.read());
        System.out.println((char)is.read());
        System.out.println((char)is.read());
    }

    private static void writeAllBytes() {
        try (OutputStream os = new FileOutputStream("all-bytes.bin")) {
            for (int i = 0; i < 256; i++) {
                os.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private static void readAllBytes() {
        try (InputStream is = new FileInputStream("all-bytes.bin")) {
            for (int i = 0; i <= 256; i++) {
                int b = is.read();
                System.out.println("[" + i + "] " + b);
                //System.out.println("[" + i + "] " + (byte)b);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private static void raf(String fileName) {
        try (RandomAccessFile f = new RandomAccessFile(fileName, "r")) { //r, rw, rws, rwd
            f.seek(6);
            System.out.println((char)f.read());
            System.out.println((char)f.read());
            System.out.println((char)f.read());
            f.seek(3);
            //f.skipBytes(1);
            System.out.println((char)f.read());
            System.out.println((char)f.read());
            System.out.println((char)f.read());
            f.seek(0);
            System.out.println(f.readLine());
            f.seek(0);
            System.out.println("FilePointer: " + f.getFilePointer());
            System.out.println(f.readLong());
            System.out.println("FilePointer: " + f.getFilePointer());
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private static void rafWrite(String fileName) {
        try (RandomAccessFile f = new RandomAccessFile(fileName, "rw")) { //r, rw, rws, rwd
            f.seek(10);
            f.write("v".getBytes());
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private static void inputStreamReader(String fileName) {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "utf-8")) {
        //try (InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "cp1251")) {
        //try (BufferedReader isr = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"))) {
        //try (BufferedReader isr = new BufferedReader(new InputStreamReader(System.in))) {
            //isr.skip(2);
            System.out.println((char)isr.read());
            System.out.println((char)isr.read());
            System.out.println((char)isr.read());

            //System.out.println(isr.readLine());
            //System.out.println(isr.readLine());
            //System.out.println("markSupported: " + isr.markSupported());
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private static void copy(String fileName) {
        try (InputStream is = new FileInputStream(fileName);
             OutputStream os = new FileOutputStream(fileName + ".copy")) {
            byte[] buf = new byte[1024];
            while (true) {
                int readed = is.read(buf);
                if (readed == -1) break;
                os.write(buf, 0, readed);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private static void byteArrayOutputStream() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(1);
        os.write(2);
        os.write(3);
        //byte[] bytes = "abcdefghijklmnopqrstuvwxyz".getBytes();
        //os.write(bytes);
        byte[] result = os.toByteArray();
        System.out.println(Arrays.toString(result));
        //os.write(4);
        //System.out.println(Arrays.toString(os.toByteArray()));
        //os.writeTo(null);
    }

    private static void dataOutputStream() {
        //запись
        try (DataOutputStream os = new DataOutputStream(new FileOutputStream("data-output-stream.bin"))) {
            os.writeDouble(Math.PI);
            os.writeLong(15L);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        //чтение
        /*try (DataInputStream os = new DataInputStream(new FileInputStream("data-output-stream.bin"))) {
            double d = os.readDouble();
            System.out.println(d);
            Long l = os.readLong();
            System.out.println(l);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }*/
    }

    private static void serialization() {
        //создание объекта
        SerializationDemo obj = new SerializationDemo();
        obj.name = "sample";
        obj.age = 42;
        obj.position = 236;
        System.out.println("obj: " + obj);
        //сериализация
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("serialization-demo.bin"))) {
            os.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        //десериализация
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("serialization-demo.bin"))) {
            Object newObj = is.readObject();
            System.out.println(newObj.getClass().getCanonicalName());
            System.out.println("obj: " + (SerializationDemo)newObj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.err);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
