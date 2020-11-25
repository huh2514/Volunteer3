package com.cookandroid.volunteer;

public class ScheduleSingleton {
    private static final ScheduleSingleton ourInstance = new ScheduleSingleton();

    public static ScheduleSingleton getInstance() {
        return ourInstance;
    }

    private ScheduleSingleton() {

    }
}
