package me.day10.smartstore.menu;

import java.util.InputMismatchException;

public abstract class TopicIntroMenu extends Menu {

    private final String TOPIC_OUTPUT;

    TopicIntroMenu(String TOPIC_OUTPUT, Menu... menus) {
        super(menus);
        this.TOPIC_OUTPUT = TOPIC_OUTPUT;
    }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setBackMenu();
        while (true) {
            try {
                print(TOPIC_OUTPUT);
                int i = inputMenu();
                return nextMenu[i];
            } catch (InputMismatchException | InvalidMenuException e) {
                print(e.getMessage());
            }
        }
    }

    private void setBackMenu() {
        StartMenu startMenu = StartMenu.getInstance();
        if (this != startMenu)
            nextMenu[nextMenu.length - 1] = startMenu;
    }
}
