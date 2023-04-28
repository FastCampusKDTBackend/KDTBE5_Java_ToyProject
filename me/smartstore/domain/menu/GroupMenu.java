package me.smartstore.domain.menu;

public class GroupMenu implements Menu {
    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {}

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
