package domain.menu;

import Service.GroupService;
import handler.exception.InputFormatException;
import handler.exception.InputRangeException;

public class GroupMenu implements Menu {

    private static final GroupService groupService = GroupService.getInstance();
    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {
    }

    @Override
    public void manage() {
        while (true) {
            try {
                int choice = groupMenu.selectMenu(new String[]{
                        "Set Parameter",
                        "View Parameter",
                        "Update Parameter",
                        "Back"
                });

                if (choice == 1) groupService.setParameter();
                if (choice == 2) groupService.showParameter();
                if (choice == 3) groupService.updateParameter();
                if (choice == 4) break;
            } catch (InputRangeException | InputFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
