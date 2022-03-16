package ru.digitalleague.ocs.liga.lessons.io;

import java.io.File;
import java.io.IOException;

public class FileSamples {
    public static void main(String[] args) throws IOException {
        String fileName = "sample.txt";
        //String fileName = "../liga-lessons/sample.txt";
        //String fileName = ".";
        //String fileName = "..";
        //String fileName = "r:/z16/ggg.txt";

        File f = new File(fileName);
        System.out.println("canRead : " + f.canRead());
        System.out.println("canWrite: " + f.canWrite());

        System.out.println("isFile     : " + f.isFile());
        System.out.println("isDirectory: " + f.isDirectory());
        System.out.println("isAbsolute : " + f.isAbsolute());
        System.out.println("isHidden   : " + f.isHidden());

        System.out.println("AbsolutePath : " + f.getAbsolutePath());
        System.out.println("CanonicalPath: " + f.getCanonicalPath());
        System.out.println("Name         : " + f.getName());
        System.out.println("Parent       : " + f.getParent());
        System.out.println("Path         : " + f.getPath());

        System.out.println("freeSpace  : " + f.getFreeSpace());
        System.out.println("usableSpace: " + f.getUsableSpace());
        System.out.println("totalSpace : " + f.getTotalSpace());
        //System.out.println("File.separator=" + File.separator);
        //System.out.println("File.pathSeparator=" + File.pathSeparator);

        /*File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("" + root.getAbsolutePath());
        }*/

        /*File[] files = new File(".").listFiles();
        for (File f : files) {
            System.out.println("" + f.getName() + (f.isDirectory() ? " [DIR]" : ""));
        }*/

        /*try {
            //File tmp = File.createTempFile("ggg", "fff");
            File tmp = File.createTempFile("ggg", "fff", new File("."));
            //tmp.deleteOnExit();
            System.out.println("tmp CanonicalPath: " + tmp.getCanonicalPath());
            //System.out.println("delete: " + tmp.delete());
            //System.out.println("delete: " + tmp.delete());
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }*/
    }
}
