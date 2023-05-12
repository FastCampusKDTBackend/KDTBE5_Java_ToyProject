package me.smartstore.menu.topic;

import me.smartstore.menu.QuitMenu;

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
            GroupIntroMenu.getInstance(),            // 1
            CustomerIntroMenu.getInstance(),         // 2
            SummaryIntroMenu.getInstance(),   // 3
            QuitMenu.getInstance()              // 4
        );
    }
}
