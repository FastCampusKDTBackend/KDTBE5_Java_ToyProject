package me.day10.smartstore.menu;

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

    private static final StartMenu INSTANCE = new StartMenu(
            OUTPUT,
            null,
            GroupMenu.getInstance(),                    // 1
            CustomerMenu.getInstance(),                 // 2
            ClassificationMenu.getInstance(),    // 3
            QuitMenu.getInstance()                       // 4
    );

    private StartMenu(String TOPIC_OUTPUT, Menu... nextMenus) {
        super(TOPIC_OUTPUT, nextMenus);
    }

    public static StartMenu getInstance() { return INSTANCE; }
}
