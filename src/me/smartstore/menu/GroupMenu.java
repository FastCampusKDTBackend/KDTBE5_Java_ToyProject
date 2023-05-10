package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
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

    private GroupType chooseGroup() {
        while ( true ) {
            try {
                System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);

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
    private void setParameter() {
        while ( true ) {
            try {
                System.out.println("\nWhich group (GENERAL (G), VIP (V), VVIP (VV))?");
                String input = nextLine(Message.END_MSG);
                GroupType groupType = GroupType.valueOf(input).replaceFullName();
                Group group = allGroups.find(groupType);

                if (group != null && group.getParameter() != null) {
                    System.out.println("\n" + group.getGroupType() + " group already exists.");
                    System.out.println("\nGroupType: " + group);
                } else {
                    Parameter parameter = new Parameter();
                    parameterManage(parameter);
                    group = new Group(parameter,groupType);
                    allGroups.add(group);
                    allCustomers.refresh(); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
                }
            } catch (InputEndException e ) {
                break;
            } catch (InputRangeException e ) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
    private void updateParameter() {
        while (true) {
            try {
                GroupType groupType = chooseGroup();
                Group group = allGroups.find(groupType);
                if (!group.equals("end") && !group.equals("END")) System.out.println("Parameter:" + group);

                Parameter parameter = group.getParameter();
                parameterManage(parameter);

                allCustomers.refresh();
                System.out.println("Parameter:" + group);
            } catch (NullPointerException e) {
                break;
            }
        }
    }
    private void viewParameter() {
        while ( true ) {
            try {
                System.out.println("\nWhich group (GENERAL (G), VIP (V), VVIP (VV))?");
                String input = nextLine(Message.END_MSG);
                GroupType groupType = GroupType.valueOf(input).replaceFullName();
                Group group = allGroups.find(groupType);
                if (!group.equals("end") && !group.equals("END")) System.out.println("\nGroupType: " + group);
            } catch (InputEndException e )  {
                break;
            } catch (NullPointerException e ) {
                System.out.println("Parameter doesn't exist , please try again");
            } catch ( IllegalArgumentException e ) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }
    }

    private void parameterManage(Parameter parameter) {
        while ( true ) { // 프로그램 실행 while
            int choice = chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"});
            if (choice == 1) {
                while (true) {
                    System.out.println("\n" + "Input Minimum Spent Time: ");
                    Integer minimumSpentTime = Integer.valueOf(nextLine(Message.END_MSG));
                    if (minimumSpentTime <= 0) {
                        throw new InputRangeException();
                    }
                    parameter.setMinimumSpentTime(minimumSpentTime);
                    break;
                }
            }
            else if (choice == 2) {
                while (true) {
                    System.out.println("\n" + "Input Minimum Total Pay: ");
                    Integer minimumTotalPay = Integer.valueOf(nextLine(Message.END_MSG));
                    if (minimumTotalPay <= 0) {
                        throw new InputRangeException();
                    }
                    parameter.setMinimumTotalPay(minimumTotalPay);
                    break;
                }
            }
            else {
                break;
            }
        }
    }
}
