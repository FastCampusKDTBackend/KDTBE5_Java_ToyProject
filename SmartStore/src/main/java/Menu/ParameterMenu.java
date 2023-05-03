package Menu;

import Group.Parameter;
import Group.GroupType;
import CustomException.*;
import Group.Groups;

public class ParameterMenu implements Menu {

    private final Groups allGroups = Groups.getInstance();
    private static ParameterMenu allParameterMenu;

    public static ParameterMenu getInstance() {
        if (allParameterMenu == null) {
            allParameterMenu = new ParameterMenu();
        }
        return allParameterMenu;
    }

    private ParameterMenu() {

    }


    @Override
    public void manage() {
        while (true) {
            int choice = allParameterMenu.chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"
            });

            if (choice == 1) initParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else if (choice == 4) break;
        }
    }

    private GroupType selectGroup() {
        while (true) {
            try {
                System.out.println("Which Group (GENERAL (G), VIP (V), VVIP (VV))?");
                String choice = nextLine(Message.END_MSG);

                GroupType selectedGroup = GroupType.valueOf(choice).shortToLong();
                return selectedGroup;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    private Parameter setMenuParameter() {
        Parameter parameter = new Parameter();
        while (true) {
            int choice = allParameterMenu.chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"
            });

            if (choice == 1) {
                System.out.println("Input Minimum Spent Time");
                parameter.setMinTime(Integer.valueOf(nextLine(Message.END_MSG)));
            } else if (choice == 2) {
                System.out.println("Input Minimum Total Pay");
                parameter.setMinPay(Integer.valueOf(nextLine(Message.END_MSG)));
            } else if (choice == 3) {
                break;
            }
        }
        return parameter;
    }

    private void initParameter() {
        while (true) {
            GroupType groupType = selectGroup();
            if (!allGroups.isExist(groupType)) {
                System.out.println(groupType + "group already exists.");
                return;
            }
            Parameter parameter = setMenuParameter();
            allGroups.findGroup(groupType).setParameter(parameter);
        }

    }

    private void viewParameter() {
        System.out.println("viewParameter");
    }

    private void updateParameter() {
        System.out.println("updateParameter");
    }
}
