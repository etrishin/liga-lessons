package ru.digitalleague.ocs.liga.lessons.threading;

import static ru.digitalleague.ocs.liga.lessons.threading.ThreadingUtils.log;

public class ThreadingSamples {
    public static void main(String[] args) {
        //newThead1();
        //newThead2();
        //newThead3();
        
        //log("sample"); // методы Thread
        
        //withoutSync();
        //sync();
        //withoutSync2();
        //sync2();

        //waitNotify();
    }

    private static void newThead1() {
        Thread t1 = new Thread(new SimpleRunnable(), "поток 1");
        t1.start();
        Thread t2 = new Thread(new SimpleRunnable(), "поток 2");
        t2.start();

        ThreadingUtils.sleep(5, 1000);
    }

    private static void newThead2() {
        Thread t1 = new Thread(new SimpleRunnableWithSleep(3, 1000), "поток 1");
        t1.start();
        Thread t2 = new Thread(new SimpleRunnableWithSleep(10, 1000), "поток 2");
        //t2.setDaemon(true);
        t2.start();

        ThreadingUtils.sleep(5, 1000);

        //вариант 1: while(true) + isAlive
        log("t1.isAlive: " + t1.isAlive());
        log("t2.isAlive: " + t2.isAlive());

        //вариант 2: join
        /*try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            log("Interrupted");
        }
        log("t1.isAlive: " + t1.isAlive());
        log("t2.isAlive: " + t2.isAlive());*/
    }

    private static void newThead3() {
        SimpleRunnableExtends t1 = new SimpleRunnableExtends("поток 1", 3, 1000);
        SimpleRunnableExtends t2 = new SimpleRunnableExtends("поток 2", 10, 1000);
        ThreadingUtils.sleep(5, 1000);
    }

    private static void withoutSync() {
        Resource r = new Resource();

        Thread t1 = new Thread(() -> { r.doSomething("этому"); }, "поток 1");
        t1.start();
        Thread t2 = new Thread(() -> { r.doSomething("примеру"); }, "поток 2");
        t2.start();
        Thread t3 = new Thread(() -> { r.doSomething("не помешает"); }, "поток 3");
        t3.start();
        Thread t4 = new Thread(() -> { r.doSomething("синхронизация"); }, "поток 4");
        t4.start();
    }

    private static void sync() {
        Resource r = new Resource();

        Thread t1 = new Thread(() -> { r.doSomethingSync("этому"); }, "поток 1");
        t1.start();
        Thread t2 = new Thread(() -> { r.doSomethingSync("примеру"); }, "поток 2");
        t2.start();
        Thread t3 = new Thread(() -> { r.doSomethingSync("не помешает"); }, "поток 3");
        t3.start();
        Thread t4 = new Thread(() -> { r.doSomethingSync("синхронизация"); }, "поток 4");
        t4.start();
    }

    private static void withoutSync2() {
        Thread t1 = new Thread(() -> { new Resource().doSomethingSync("этому"); }, "поток 1");
        t1.start();
        Thread t2 = new Thread(() -> { new Resource().doSomethingSync("примеру"); }, "поток 2");
        t2.start();
        Thread t3 = new Thread(() -> { new Resource().doSomethingSync("не помешает"); }, "поток 3");
        t3.start();
        Thread t4 = new Thread(() -> { new Resource().doSomethingSync("синхронизация"); }, "поток 4");
        t4.start();
    }

    private static void sync2() {
        Resource r = new Resource();

        Thread t1 = new Thread(() -> {
            synchronized (r) {
                r.doSomething("этому"); 
            }
        }, "поток 1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (r) {
                r.doSomething("примеру");
            }
        }, "поток 2");
        t2.start();

        Thread t3 = new Thread(() -> {
            synchronized (r) {
                r.doSomething("не помешает");
            }
        }, "поток 3");
        t3.start();

        Thread t4 = new Thread(() -> {
            synchronized (r) {
                r.doSomething("синхронизация");
            }
        }, "поток 4");
        t4.start();
    }

    private static void waitNotify() {
        AdvancedResource r = new AdvancedResource();

        Thread t1 = new Thread(() -> { log("получено: " + r.get()); }, "поток 1");
        t1.start();

        //ThreadingUtils.sleep(1, 5000);
        
        Thread t2 = new Thread(() -> { r.set(5); }, "поток 2");
        t2.start();
    }
}
