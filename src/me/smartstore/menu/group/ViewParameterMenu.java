package me.smartstore.menu.group;

import me.smartstore.group.Group;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.GroupIntroMenu;

public class ViewParameterMenu extends GroupParameterMenu {

    private static class InstanceHolder {
        private static final ViewParameterMenu INSTANCE = new ViewParameterMenu();
    }
    private ViewParameterMenu() {}
    public static ViewParameterMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected void setNextMenus() {}

    @Override
    protected Menu handleAndMoveToNextMenu(Group group) {
        print(group);
        return GroupIntroMenu.getInstance();
    }
}
