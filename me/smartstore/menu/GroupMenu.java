package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.NullArgumentException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;

public class GroupMenu implements Menu {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    // singleton
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
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) setParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else break; // choice == 4
        }

    }

    public void setParameter() { // 초기화할 때만 호출 가능
        while ( true ) {
            try {
                GroupType groupType = chooseGroup();
                if(groupType == null) {
                    break;
                }
                // GroupType에 해당하는 group 객체를 찾아야 함
                Group group = allGroups.find(groupType);
                if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                    System.out.println("\n" + group.getGroupType() + " group already exists.");
                    System.out.println("\n" + group);
                } else {
                    Parameter parameter = new Parameter();
                    // time, pay 사용자 입력받은 후, 설정 필요
                    inputParameter(parameter);

                    if(parameter.getMinPay() == null || parameter.getMinTime() == null) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
                    }else {
                        Group addGroup = new Group();
                        addGroup.setParameter(parameter);
                        addGroup.setGroupType(groupType);
                        allGroups.add(addGroup);
                        allCustomers.refresh(allGroups); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
                        break;
                    }
                }
            }catch (NullArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
                System.out.println();
                break;
            }

        }
    }

    private void inputParameter(Parameter parameter) {
        while(true) {
            try {
                int choice = chooseMenu(new String[]{
                        "Minimum Spent Time",
                        "Minimum Total Pay",
                        "Back"
                });
                if (choice == 1) {
                    System.out.println("Input Minimum Spent Time");
                    parameter.setMinTime(Integer.parseInt(nextLine(Message.END_MSG)));
                    System.out.println(parameter);
                } else if (choice == 2) {
                    System.out.println("Input Minimum Total Pay");
                    parameter.setMinPay(Integer.parseInt(nextLine(Message.END_MSG)));
                    System.out.println(parameter);
                } else {
                    if(parameter.getMinPay() == null || parameter.getMinTime() == null) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
                    }else {
                        System.out.printf(
                                "＿人人人人人人人人人人人人人人人人人人人人人人人人人人人人人人＿\n" +
                                "＞　Success !!! %s  ＜\n" +
                                "￣^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y￣\n" +
                                "       .A__A    ✨\uD83C\uDF82✨    A__A\n" +
                                "       ( •⩊•)  _______ (•⩊• )\n" +
                                "       (>\uD83C\uDF70>)   |   |   (<\uD83D\uDD2A<)\n", parameter);
                        break;
                    }
                }
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }
    }

    private void viewParameter() {
        while(true) {
            GroupType groupType = chooseGroup();
            if(groupType == null) {
                break;
            }
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) {
                System.out.println("\n ─────────[" + group.getGroupType() + "]─────────");
                System.out.println(group);
                System.out.println("──────────────────────────────────────────");
            } else {
                System.out.printf("Please Set Parameter first %s\n", groupType);
//                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
                System.out.println();
            }
        }

    }

    private void updateParameter() {
        while ( true ) {
            GroupType groupType = chooseGroup();
            if(groupType == null) {
                break;
            }
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) {
                inputParameter(group.getParameter());
                allCustomers.refresh(allGroups);
            } else {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
                System.out.println();
            }
        }
    }

    public GroupType chooseGroup() {
        while ( true ) {
            try {
                System.out.println(
                        "┬ ┬┬ ┬┬┌─┐┬ ┬  ┌─┐┬─┐┌─┐┬ ┬┌─┐\n" +
                        "│││├─┤││  ├─┤  │ ┬├┬┘│ ││ │├─┘\n" +
                        "└┴┘┴ ┴┴└─┘┴ ┴  └─┘┴└─└─┘└─┘┴   \n" +
                        "─────────────────────────────────");
                GroupType[] groupTypes = GroupType.values();
                for(int i = 0; i < groupTypes.length / 2; i++) {
                    System.out.printf("         → %s (%s) ? \n", groupTypes[i], groupTypes[i + groupTypes.length / 2]);
                }
                System.out.println("─────────────────────────────────");
                String choice = nextLine(Message.END_MSG);
                // group (str) -> GroupType (enum)
                // "VIP" -> GroupType.VIP
                GroupType groupType = GroupType.valueOf(choice).replaceFullName();
                return groupType;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
}
