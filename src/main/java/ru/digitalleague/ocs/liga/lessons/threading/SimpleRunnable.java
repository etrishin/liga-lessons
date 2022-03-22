package ru.digitalleague.ocs.liga.lessons.threading;

import static ru.digitalleague.ocs.liga.lessons.threading.ThreadingUtils.log;

public class SimpleRunnable implements Runnable {

    /*public static void main(String[] args) {
        log("start");
        SimpleRunnable obj1 = new SimpleRunnable();
        obj1.run();
        SimpleRunnable obj2 = new SimpleRunnable();
        obj2.run();
        log("finish");
    }*/

    @Override
    public void run() {
        log("in runnable");
    }

}
