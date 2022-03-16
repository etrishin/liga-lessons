package ru.digitalleague.ocs.liga.lessons.io;

public class SerializationDemo implements java.io.Serializable {
    public String name;
    // transient
    public Integer age;
    public long position;

    @Override
    public String toString() {
        return name + ", " + age + ", " + position;
    }
}
