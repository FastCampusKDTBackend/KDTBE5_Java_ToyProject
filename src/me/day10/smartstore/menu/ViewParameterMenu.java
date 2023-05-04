package me.day10.smartstore.menu;

import me.day10.smartstore.group.GroupType;

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
                GroupType groupType = GroupType.getGroupTypeByString(groupName);
                print(groupType);
            } catch (BackMenuException e) {
                print(e.getMessage());
                return backMenu;
            } catch (InvalidGroupTypeException e) {
                print(e.getMessage());
            }
        }
    }
}
