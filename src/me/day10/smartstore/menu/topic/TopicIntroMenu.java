package me.day10.smartstore.menu.topic;

import me.day10.smartstore.menu.exception.InvalidMenuException;
import me.day10.smartstore.menu.Menu;

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
        return inputMenuAndMoveToNextMenu();
    }

    protected void setBackMenu() {
        StartMenu startMenu = StartMenu.getInstance();
        if (this != startMenu)
            setBackMenu(startMenu);
    }

    protected Menu inputMenuAndMoveToNextMenu() {
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
}
