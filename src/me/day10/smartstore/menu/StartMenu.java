package me.day10.smartstore.menu;

import java.util.InputMismatchException;

public class StartMenu extends TopicIntroMenu {

    private static final String OUTPUT =
                    '\n' +
                    "========= Start Menu =========" + '\n' +
                    "1. " + "Group" + '\n' +
                    "2. " + "Customer Data" + '\n' +
                    "3. " + "Classification Summary" + '\n' +
                    "4. " + "Quit" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private static final StartMenu INSTANCE = new StartMenu(
            OUTPUT,
            null,
            GroupMenu.getInstance(),                    // 1
            CustomerMenu.getInstance(),                 // 2
            ClassificationSummaryMenu.getInstance(),    // 3
            EndMenu.getInstance()                       // 4
    );

    private StartMenu(String TOPIC_OUTPUT, Menu... nextMenus) {
        super(TOPIC_OUTPUT, nextMenus);
    }

    public static StartMenu getInstance() { return INSTANCE; }
}
