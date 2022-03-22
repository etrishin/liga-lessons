package ru.digitalleague.ocs.liga.lessons.threading;

import static ru.digitalleague.ocs.liga.lessons.threading.ThreadingUtils.log;

public class AdvancedResource {
    private int value = -1;
    
    public synchronized int get() {
        while (value == -1) {
            try {
                log("get waiting...");
                wait();
            } catch (InterruptedException e) {
                log("Interrupted");
            }
        }
        int result = value;
        value = -1;
        notify();
        return result;
    }

    public synchronized void set(int n) {
        while (value != -1) {
            try {
                log("put waiting...");
                wait();
            } catch (InterruptedException e) {
                log("Interrupted");
            }
        }
        this.value = n;
        log("set: " + value);
        notify();
    }
}
