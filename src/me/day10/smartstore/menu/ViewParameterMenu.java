package me.day10.smartstore.menu;

import me.day10.smartstore.group.Group;

public class ViewParameterMenu extends Menu {

    private static final ViewParameterMenu INSTANCE = new ViewParameterMenu();
    private ViewParameterMenu() {}
    public static ViewParameterMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        Menu backMenu = GroupMenu.getInstance();
        while (true) {
            print(GROUP_OUTPUT);
            try {
                String groupName = inputGroupName();
                Group group = Group.getGroupByString(groupName);
                print(group);
            } catch (InputIsEndException e) {
                print(e.getMessage());
                return backMenu;
            } catch (InvalidGroupNameException e) {
                print(e.getMessage());
            }
        }
    }
}
