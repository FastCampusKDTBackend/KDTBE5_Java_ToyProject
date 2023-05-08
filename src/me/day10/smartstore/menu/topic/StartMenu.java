package me.day10.smartstore.menu.topic;

import me.day10.smartstore.menu.QuitMenu;

public class StartMenu extends TopicIntroMenu {

    private static final String OUTPUT =
                    '\n' +
                    "========= Start Menu =========" + '\n' +
                    "1. " + "Group" + '\n' +
                    "2. " + "Customer" + '\n' +
                    "3. " + "Classification" + '\n' +
                    "4. " + "Quit" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private static final StartMenu INSTANCE = new StartMenu();

    private StartMenu() { super(OUTPUT); }

    public static StartMenu getInstance() { return INSTANCE; }

    @Override
    protected void setNextMenus() {
        setNextMenus(
            GroupMenu.getInstance(),            // 1
            CustomerMenu.getInstance(),         // 2
            ClassificationMenu.getInstance(),   // 3
            QuitMenu.getInstance()              // 4
        );
    }
}
