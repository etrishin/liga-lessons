package ru.digitalleague.ocs.liga.lessons.threading;

public class SimpleRunnableExtends extends Thread {
    private int count;
    private long timer;

    public SimpleRunnableExtends(String name, int count, long timer) {
        super(name);
        this.count = count;
        this.timer = timer;
        start();
    }

    @Override
    public void run() {
        ThreadingUtils.sleep(count, timer);
    }
}
