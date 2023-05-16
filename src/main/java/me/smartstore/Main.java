package me.smartstore;

import me.smartstore.runner.MainRunner;

public class Main {
    public static void main(String[] args) {
        MainRunner mainRunner = MainRunner.getInstance();
        mainRunner.run();
    }
}
