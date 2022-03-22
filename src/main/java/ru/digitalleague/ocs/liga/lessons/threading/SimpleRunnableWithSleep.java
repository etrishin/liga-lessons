package ru.digitalleague.ocs.liga.lessons.threading;

public class SimpleRunnableWithSleep implements Runnable {
    private int count;
    private long timer;

    public SimpleRunnableWithSleep(int count, long timer) {
        this.count = count;
        this.timer = timer;
    }

    @Override
    public void run() {
        ThreadingUtils.sleep(count, timer);
    }
}
