package ru.digitalleague.ocs.liga.lessons.threading;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import static ru.digitalleague.ocs.liga.lessons.threading.ThreadingUtils.log;

public class ParallelSamples {
    public static void main(String[] args) {
        //semaphore();
        //сountDownLatch();
        
        //simpleExecutor();
        //callableAndFuture();
    }

    private static void semaphore() {
        long timer = System.currentTimeMillis();
        Semaphore s = new Semaphore(1, true); // = Монитор (8sec)
        //Semaphore s = new Semaphore(4, true); // 2 sec
        //Semaphore s = new Semaphore(2, true); // 4 sec

        //System.out.println("s.tryAcquire(5): " + s.tryAcquire(5));
        //System.out.println("s.tryAcquire(1): " + s.tryAcquire(1));
        //System.out.println("s.tryAcquire(1): " + s.tryAcquire(1));
        
        Thread t1 = new Thread(new SimpleRunnableWithSemaphore(s), "поток 1");
        t1.start();
        Thread t2 = new Thread(new SimpleRunnableWithSemaphore(s), "поток 2");
        t2.start();
        Thread t3 = new Thread(new SimpleRunnableWithSemaphore(s), "поток 3");
        t3.start();
        Thread t4 = new Thread(new SimpleRunnableWithSemaphore(s), "поток 4");
        t4.start();
        
        //ThreadingUtils.sleep(5, 1000);
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            log("Interrupted");
        }
        System.out.println("total time: " + (System.currentTimeMillis() - timer));
    }

    private static void сountDownLatch() {
        CountDownLatch cdl = new CountDownLatch(5000);
        Thread t1 = new Thread(new SimpleRunnableWithCountDowhLatch(cdl), "поток 1");
        t1.start();
        Thread t2 = new Thread(new SimpleRunnableWithCountDowhLatch(cdl), "поток 2");
        t2.start();
        Thread t3 = new Thread(new SimpleRunnableWithCountDowhLatch(cdl), "поток 3");
        t3.start();
        
        try {
            cdl.await();
        } catch (InterruptedException e) {
            log("Interrupted");
        }
        log("finish");
    }

    private static void simpleExecutor() {
        ExecutorService es = Executors.newFixedThreadPool(2);
        
        es.execute(new SimpleRunnableWithSleep(5, 1000));
        es.execute(new SimpleRunnableWithSleep(5, 1000));
        es.execute(new SimpleRunnableWithSleep(5, 1000));
        es.execute(new SimpleRunnableWithSleep(5, 1000));
        es.execute(new SimpleRunnableWithSleep(5, 1000));

        es.shutdown();
    }

    private static void callableAndFuture() {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log("from call!");
                Thread.sleep(5000);
                //if (1 == 1) throw new IllegalArgumentException("test");
                return "Привет1";
            }
        };
        Callable<String> c2 = () -> "Привет2";

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> future = es.submit(c);
        Future<String> future2 = es.submit(c2);
        log("all tasks submitted");
        
        try {
            log(future.get());
            log(future2.get());
        } catch (InterruptedException e) {
            log("Interrupted");
        } catch (ExecutionException e) {
            log("ExecutionException: " + e.getMessage() + " ("+e.getCause().getClass().getName()+")");
        }
        es.shutdown();
    }

}
