package me.smartstore.menu.topic;

import me.smartstore.menu.Menu;

import java.util.InputMismatchException;

public abstract class TopicIntroMenu extends Menu {

    private final String TOPIC_OUTPUT;

    TopicIntroMenu(String TOPIC_OUTPUT) { this.TOPIC_OUTPUT = TOPIC_OUTPUT; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        return inputMenuAndMoveToNextMenu();
    }

    protected Menu inputMenuAndMoveToNextMenu() {
        while (true) {
            try {
                print(TOPIC_OUTPUT);
                int menuIdx = inputMenuIdx();
                return getNextMenu(menuIdx);
            } catch (InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }
}
