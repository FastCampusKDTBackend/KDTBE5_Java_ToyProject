package me.day10.smartstore.menu.group;

import me.day10.smartstore.group.Group;
import me.day10.smartstore.menu.exception.InputIsEndException;
import me.day10.smartstore.menu.exception.InvalidGroupNameException;
import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.GroupMenu;

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
