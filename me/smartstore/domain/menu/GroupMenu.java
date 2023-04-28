package me.smartstore.domain.menu;

import me.smartstore.domain.group.Parameter;
public class GroupMenu implements Menu {
    private static final Integer GROUP_TYPE_COUNT = 3;
    private static GroupMenu groupMenu;

    private boolean[] isExitType;
    private Parameter[] parameters;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {
        isExitType = new boolean[GROUP_TYPE_COUNT];
        parameters = new Parameter[GROUP_TYPE_COUNT];
    }

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"
            });

            if (choice == 1) {
                createParameter();
            } else if (choice == 2) {
                findParameter();
            } else if (choice == 3) {
                updateParameter();
            } else {
                break;
            }
        }
    }

    private void createParameter() {};

    private void findParameter() {};

    private void updateParameter() {};
}
