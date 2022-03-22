package ru.digitalleague.ocs.liga.lessons.threading;

public class ThreadingUtils {

    public static void sleep(int count, long timer) {
        for (int i = count; i > 0; i--) {
            try {
                Thread.sleep(timer);
                log("" + i);
            } catch (InterruptedException e) {
                log("Interrupted");
            }
        }
    }

    public static void log(String msg) {
        Thread t = Thread.currentThread();
        System.out.println(t + ": " + msg);

        /*System.out.println("name    : " + t.getName());
        System.out.println("priority: " + t.getPriority());
        System.out.println("isDaemon: " + t.isDaemon());
        //t.set

        System.out.println("id     : " + t.getId());
        System.out.println("state  : " + t.getState());
        System.out.println("isAlive: " + t.isAlive());
        ThreadGroup tg = t.getThreadGroup();
        System.out.println("tg name       : " + tg.getName());
        System.out.println("tg MaxPriority: " + tg.getMaxPriority());
        System.out.println("tg Parent     : " + tg.getParent());
        System.out.println("tg activeCount     : " + tg.activeCount());
        System.out.println("tg activeGroupCount: " + tg.activeGroupCount());*/
    }
}
