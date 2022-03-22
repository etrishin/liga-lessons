package ru.digitalleague.ocs.liga.lessons.threading;

public class Resource {
    
    public synchronized void doSomethingSync(String str) {
        doSomething(str);
    }

    public void doSomething(String str) {
        System.out.print("[" + str);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}
