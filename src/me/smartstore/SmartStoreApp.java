package me.smartstore;

import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.StartMenu;

public class SmartStoreApp {

    private static final SmartStoreApp INSTANCE = new SmartStoreApp();
    private SmartStoreApp() {}
    public static SmartStoreApp getInstance() { return INSTANCE; }

    public SmartStoreApp test() {
        return this;
    }

    public void run() {
        Menu menu = StartMenu.getInstance();
        do {
            Menu nextMenu = menu.printAndInputAndGetNextMenu();
            if (nextMenu == null)
                return;
            nextMenu.setPrevMenu(menu);
            menu = nextMenu;
        } while (true);
    }
}
