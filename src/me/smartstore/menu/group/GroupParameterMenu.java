package me.smartstore.menu.group;

import me.smartstore.group.Group;
import me.smartstore.menu.Menu;
import me.smartstore.menu.exception.InputIsEndException;
import me.smartstore.menu.exception.InvalidGroupNameException;
import me.smartstore.menu.topic.GroupMenu;

public abstract class GroupParameterMenu extends Menu {

    private static final String GROUP_OUTPUT =
                    '\n' +
                    "Which group (GENERAL (G), VIP (V), VVIP (VV))?\n"
                    + END_INPUT;
    private Group groupCache;

    @Override
    public Menu printAndInputAndGetNextMenu() {
        if (getPrevMenu() == GroupMenu.getInstance()) {
            while (true) {
                print(GROUP_OUTPUT);
                try {
                    String groupName = inputStringOrEnd();
                    groupCache = Group.getGroupByString(groupName);
                    break;
                } catch (InputIsEndException e) {
                    print(e.getMessage());
                    return GroupMenu.getInstance();
                } catch (InvalidGroupNameException e) {
                    print(e.getMessage());
                }
            }
        }
        return handleAndMoveToNextMenu(groupCache);
    }

    @Override
    protected void setNextMenus() {}

    protected abstract Menu handleAndMoveToNextMenu(Group group);
}
