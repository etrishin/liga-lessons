package ru.digitalleague.ocs.liga.lessons.threading;

import java.util.concurrent.CountDownLatch;

import static ru.digitalleague.ocs.liga.lessons.threading.ThreadingUtils.log;

public class SimpleRunnableWithCountDowhLatch implements Runnable {
    private CountDownLatch latch;

    public SimpleRunnableWithCountDowhLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        while (latch.getCount() > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log("Interrupted");
            }
            latch.countDown();
        }
        log("exit");
    }
}
