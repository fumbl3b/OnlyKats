package com.example.onlykats.repo.remote;

public class TestSingleton {

    private static TestSingleton INSTANCE;

    private TestSingleton() {

    }

    public static TestSingleton getInstance() {
        if (INSTANCE == null) INSTANCE = new TestSingleton();
        return INSTANCE;
    }
}
