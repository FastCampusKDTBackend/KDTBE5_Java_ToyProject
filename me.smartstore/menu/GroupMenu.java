package menu;

import customer.Customers;
import exception.InputEndException;
import group.Group;
import group.GroupType;
import group.Groups;
import group.Parameter;
import util.Message;

public class GroupMenu implements Menu {
    private final group.Groups allGroups = Groups.getInstance();
    private final customer.Customers allCustomers = Customers.getInstance();
    // singleton
    private static menu.GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new menu.GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) setParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else break;

        }

    }

    public group.GroupType chooseGroup() {
        while ( true ) {
            try {
                System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(util.Message.END_MSG);
                // group (str) -> GroupType (enum)
                // "VIP" -> GroupType.VIP

                group.GroupType groupType = group.GroupType.valueOf(choice).replaceFullName();
                return groupType;
            } catch (InputEndException e) {
                System.out.println(util.Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public void setParameter() { // 초기화할 때만 호출 가능
        while ( true ) {
            GroupType groupType = chooseGroup();
            if (groupType == null) {
                break; // chooseGroup()에서 InputEndException 발생시 루프를 빠져나감
            }

            // GroupType에 해당하는 group 객체를 찾아야 함
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                group.Parameter parameter = new Parameter();

                // time, pay 사용자 입력받은 후, 설정 필요
                System.out.print("Enter minimum payment: ");
                int minPayment = nextInt(Message.END_MSG);
                parameter.setMinPay(minPayment);

                System.out.print("Enter minimum time: ");
                int minTime = nextInt(Message.END_MSG);
                parameter.setMinTime(minTime);

                group.setParameter(parameter);
                allCustomers.refresh(allGroups); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
                break; // 루프를 빠져나감
            }
        }
    }
    public void viewParameter() {
        GroupType groupType = chooseGroup();
        Group group = allGroups.find(groupType);

        if (group != null && group.getParameter() != null) {
            System.out.println("\n" + group);
        } else {
            System.out.println("\n" + groupType + " group is not initialized yet.");
        }
    }
    public void updateParameter() {
        GroupType groupType = chooseGroup();
        Group group = allGroups.find(groupType);

        if (group != null && group.getParameter() != null) {
            group.Parameter parameter = group.getParameter();

            System.out.println("\nCurrent Parameter:");
            System.out.println(parameter);

            System.out.print("\nEnter the new values:\n- Minimum Payment: ");
            int minPayment = nextInt(util.Message.END_MSG);
            System.out.print("- Minimum Time: ");
            int minTime = nextInt(util.Message.END_MSG);

            parameter.setMinPay(minPayment);
            parameter.setMinTime(minTime);

            group.setParameter(parameter);
            allCustomers.refresh(allGroups);
        } else {
            System.out.println("\n" + groupType + " group is not initialized yet.");
        }
    }

    private int nextInt(String endMsg) {
        while (true) {
            try {
                System.out.print("\nEnter an integer value: ");
                String input = nextLine(endMsg);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return 0;
            }
        }
    }
    public static GroupType replaceFullName(String name) {
        switch (name.toUpperCase()) {
            case "G":
                return GroupType.GENERAL;
            case "V":
                return GroupType.VIP;
            case "VV":
                return GroupType.VVIP;
            default:
                return null;
        }
    }



}

