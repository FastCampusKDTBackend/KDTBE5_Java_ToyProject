package me.smartstore.menu.group;

import me.smartstore.group.Group;
import me.smartstore.menu.exception.InputIsEndException;
import me.smartstore.menu.exception.InvalidGroupNameException;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.GroupMenu;

public class ViewParameterMenu extends Menu {

    private static class InstanceHolder {
        private static final ViewParameterMenu INSTANCE = new ViewParameterMenu();
    }
    private ViewParameterMenu() {}
    public static ViewParameterMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        while (true) {
            print(GROUP_OUTPUT);
            try {
                String groupName = inputStringOrEnd();
                Group group = Group.getGroupByString(groupName);
                print(group);
            } catch (InputIsEndException e) {
                print(e.getMessage());
                return getBackMenu();
            } catch (InvalidGroupNameException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(GroupMenu.getInstance());
    }
}
