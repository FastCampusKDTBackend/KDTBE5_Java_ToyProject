package me.day10.smartstore.menu;

public class GroupMenu extends TopicIntroMenu {

    private static final String MENU_OUTPUT =
                    '\n' +
                    "========= Group Menu =========" + '\n' +
                    "1. " + "Set " + "Parameter" + '\n' +
                    "2. " + "View " + "Parameter" + '\n' +
                    "3. " + "Update " + "Parameter" + '\n' +
                    "4. " + "Back" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private static final GroupMenu INSTANCE = new GroupMenu(
            MENU_OUTPUT,
            null,
            SetParameterMenu.getInstance(),     // 1
            ViewParameterMenu.getInstance(),    // 2
            UpdateParameterMenu.getInstance(),  // 3
            null                                // 4 (StartMenu 순환 의존)
    );

    private GroupMenu(String TOPIC_OUTPUT, Menu... nextMenus) {
        super(TOPIC_OUTPUT, nextMenus);
    }

    public static GroupMenu getInstance() { return INSTANCE; }
}
