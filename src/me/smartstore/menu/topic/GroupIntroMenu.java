package me.smartstore.menu.topic;

import me.smartstore.menu.group.UpdateParameterMenu;
import me.smartstore.menu.group.ViewParameterMenu;

public class GroupIntroMenu extends TopicIntroMenu {

    private static final String GROUP_MENU_OUTPUT =
                    '\n' +
                    "========= Group Menu =========" + '\n' +
                    "1. " + "Update " + "Parameter" + '\n' +
                    "2. " + "View " + "Parameter" + '\n' +
                    "3. " + "Back" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";
    private static final GroupIntroMenu INSTANCE = new GroupIntroMenu();

    private GroupIntroMenu() { super(GROUP_MENU_OUTPUT); }

    public static GroupIntroMenu getInstance() { return INSTANCE; }

    @Override
    protected void setNextMenus() {
        setNextMenus(
                UpdateParameterMenu.getInstance(),  // 1
                ViewParameterMenu.getInstance(),    // 2
                StartMenu.getInstance()             // 3
        );
    }
}
