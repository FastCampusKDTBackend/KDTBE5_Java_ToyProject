package me.smartstore.menu.topic;

import me.smartstore.menu.group.SetParameterMenu;
import me.smartstore.menu.group.UpdateParameterMenu;
import me.smartstore.menu.group.ViewParameterMenu;

public class GroupMenu extends TopicIntroMenu {

    private static final String GROUP_MENU_OUTPUT =
                    '\n' +
                    "========= Group Menu =========" + '\n' +
                    "1. " + "Set " + "Parameter" + '\n' +
                    "2. " + "View " + "Parameter" + '\n' +
                    "3. " + "Update " + "Parameter" + '\n' +
                    "4. " + "Back" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";
    private static final GroupMenu INSTANCE = new GroupMenu();

    private GroupMenu() { super(GROUP_MENU_OUTPUT); }

    public static GroupMenu getInstance() { return INSTANCE; }

    @Override
    protected void setNextMenus() {
        setNextMenus(
                SetParameterMenu.getInstance(),     // 1
                ViewParameterMenu.getInstance(),    // 2
                UpdateParameterMenu.getInstance(),  // 3
                StartMenu.getInstance()             // 4
        );
    }
}
