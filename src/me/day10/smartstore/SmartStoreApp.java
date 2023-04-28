package me.day10.smartstore;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.StartMenu;

public class SmartStoreApp {

    private static final SmartStoreApp INSTANCE = new SmartStoreApp();
    private SmartStoreApp() {}
    public static SmartStoreApp getInstance() {
        return INSTANCE;
    }

    public SmartStoreApp test() {
        return this;
    }

    public void run() {
    }
}
