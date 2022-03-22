package ru.digitalleague.ocs.liga.lessons.threading;

import java.util.concurrent.Semaphore;

import static ru.digitalleague.ocs.liga.lessons.threading.ThreadingUtils.log;

public class SimpleRunnableWithSemaphore implements Runnable {
    private Semaphore semaphore;

    public SimpleRunnableWithSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            log("waiting for resource...");
            semaphore.acquire();
            //semaphore.acquire(2);
            Thread.sleep(2000);
            log("resource ok");
        } catch (InterruptedException e) {
            log("Interrupted");
        } finally {
            log("try to release resource...");
            semaphore.release();
            //semaphore.release(2);
            log("resource released");
        }
    }
}
