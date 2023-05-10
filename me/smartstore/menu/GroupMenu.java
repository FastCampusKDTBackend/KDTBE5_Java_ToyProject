package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;
import java.util.Scanner;


public class GroupMenu implements Menu {
    Scanner sc = new Scanner(System.in);

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
            else if (choice == 2) { viewParameter(); }
            else if (choice == 3) { updateParameter();}
            //else if (choice == 4) {MainMenu.getInstance().manage();}
            else break; // choice == 4
        }

    }

    public GroupType chooseGroup() {
        while ( true ) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
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

    public void setParameter() { // 초기화할 때만 호출 가능
        while ( true ) {

            GroupType groupType = chooseGroup();
            if (groupType == null) { // InputEndException 발생 시 GroupMenu으로 이동
                break;
            }
            // GroupType에 해당하는 group 객체를 찾아야 함
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                Parameter parameter = new Parameter();
                setPaywithTime(allGroups.find(groupType).getParameter());

                group.setParameter(parameter);
                allCustomers.refresh(); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
            }
        }
    }

    public void setPaywithTime(Parameter parameter) { //
        while(true) {
            int choice = chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back",
                    });
            if (choice == 1) {
                System.out.println("Input Minimum Spent Time:");
                String input = nextLine(Message.END_MSG);
                if (input.equalsIgnoreCase("end")) {
                    System.out.println(Message.ERR_MSG_INPUT_END);
                    break;
                }
                try {
                    parameter.setMinTime(Integer.valueOf(input));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number or 'end'.");
                }
            }
            else if (choice == 2) {
                System.out.println("Input Minimum Total Pay:");
                String input = nextLine(Message.END_MSG);
                if (input.equalsIgnoreCase("end")) {
                    System.out.println(Message.ERR_MSG_INPUT_END);
                   break;
                }
                try {
                    parameter.setMinPay(Integer.valueOf(input));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number or 'end'.");
                }
            }else
                break;
            }

        }



    public void viewParameter() {
        while (true) {
            GroupType groupType = chooseGroup();
            if (groupType == null) { //end입력시 이동
                break;
            }
            Group group = allGroups.find(groupType);
            System.out.println("GroupType: " + groupType);
            System.out.println("Parameter: " + group.getParameter());


        }
    }

    public void updateParameter() {
        while(true) {
            GroupType groupType = chooseGroup();
            if(groupType ==null) break;
            setPaywithTime(allGroups.find(groupType).getParameter());

        }
    }
}
