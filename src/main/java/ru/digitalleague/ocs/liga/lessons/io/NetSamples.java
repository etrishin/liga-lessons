package ru.digitalleague.ocs.liga.lessons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NetSamples {
    public static void main(String[] args) {
        //client("www.example.com", 80);

        //server();
        //client("localhost", 4321);

        nioClient("localhost", 4321);
    }

    private static void client(String host, int port) {
        try (Socket s = new Socket(host, port);
              InputStream is = s.getInputStream();
              OutputStream os = s.getOutputStream()) {
            //s.setSoTimeout(5000);
            //os.write("GET / HTTP/1.1\nHost: www.example.com\n\n".getBytes());
            //System.out.println("available: " + is.available());
            //System.in.read();
            //System.out.println("available: " + is.available());
            byte[] buf = new byte[4096];
            int readed = is.read(buf);
            System.out.println("readed: " + readed);
            if (readed != -1) {
                System.out.println(new String(buf, 0, readed));
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
/*
GET / HTTP/1.1
Host: www.example.com

*/
    }

    private static void server() {
        try (ServerSocket ss = new ServerSocket(4321)) {
            System.out.println("Server started on port " + ss.getLocalPort());
            while (true) {
                System.out.println("wait connection...");
                try (Socket s = ss.accept();
                       InputStream is = s.getInputStream();
                       OutputStream os = s.getOutputStream()) {
                    System.out.println("connection accepted");
                    byte[] buf = new byte[4096];
                    int readed = is.read(buf);
                    System.out.println("readed: " + readed);
                    if (readed != -1) {
                        String request = new String(buf, 0, readed);
                        System.out.println(request);
                        String result = new StringBuilder(request).reverse().toString();
                        os.write(result.getBytes());
                    }
                } catch (IOException e) {
                    System.out.println("Erron on Socket layer:");
                    e.printStackTrace(System.out);
                }
            }
        } catch (IOException e) {
            System.out.println("Erron on ServerSocket layer:");
            e.printStackTrace(System.out);
        }
    }

    private static void nioClient(String host, int port) {
        try (SocketChannel sc = SocketChannel.open(new InetSocketAddress(host, port))) {
            sc.configureBlocking(false);
            ByteBuffer in = ByteBuffer.allocate(1024);
            ByteBuffer out = ByteBuffer.allocate(1024);
            boolean sent = false;
            while (true) {
                int readed = sc.read(in);
                System.out.println("readed: " + readed);
                if (readed == -1) {
                    break;
                }
                if (readed > 0) {
                    System.out.println("RESPONSE: " + new String(in.array(), 0, readed));
                }
                if (readed == 0 && !sent) {
                    out.put("hello".getBytes());
                    out.flip();
                    while (out.hasRemaining()) {
                        int sended = sc.write(out);
                        System.out.println("SENDED: " + sended);
                    }
                    sent = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
