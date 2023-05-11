package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;

public class GroupMenu implements Menu{

    private static GroupMenu groupMenu;
    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();

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
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"
            });
            if (choice == 1) setParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else break;
        }
    }

    public GroupType chooseGroup() {
        while(true){
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                // group (str) -> GroupType (enum)
                // "VIP" -> GroupType.VIP
                GroupType groupType = GroupType.valueOf(choice).replaceFullName(); // String -> enum
                return groupType;
            }catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }

    }

    public void setParameter() {
        while (true) {
            GroupType groupType = chooseGroup();
            if(groupType == null){
                break;
            }

            //GroupType에 해당하는 group객체를 찾아야함
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                group = new Group(new Parameter(), groupType);
                allGroups.add(group);
                while (true) {
                    int choice = chooseMenu(new String[]{
                            "Minimum Spent Time",
                            "Minimum Total Pay",
                            "Back"});
                    if (choice == 1) {
                        setMinimumSpentTime(group);
                        continue;
                    }
                    if (choice == 2) {
                        setMinimumTotalPay(group);
                        continue;
                    }
                    if (choice == 3)
                        break;
                }
                allCustomers.refresh();

            }

        }
    }

    public void setMinimumSpentTime(Group group) {
        while (true) {
            try {
                System.out.printf("Input Minimum Spent Time:");
                String spentTime = nextLine(Message.END_MSG);
                Parameter parameter = group.getParameter();
                parameter.setMinTime(Integer.parseInt(spentTime));
                group.setParameter(parameter);
                System.out.println(group.getParameter());
                allCustomers.refresh();
                break;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }

    }

    public void setMinimumTotalPay(Group group) {
        while (true) {
            try {
                System.out.printf("Input Minimum Total Pay:");
                String totalPay = nextLine(Message.END_MSG);
                Parameter parameter = group.getParameter();
                parameter.setMinPay(Integer.parseInt(totalPay));
                group.setParameter(parameter);
                System.out.println(group.getParameter());
                allCustomers.refresh();
                break;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }


    public void viewParameter() {
        while (true) {
            GroupType groupType = chooseGroup();
            if (groupType == null) {
                break;
            }
            Group group = allGroups.find(groupType);
            System.out.println(groupType);
            System.out.println(group.getParameter());
        }
    }


    public void updateParameter() {
        while (true) {
            GroupType groupType = chooseGroup();
            if (groupType == null) {
                break;
            }
            Group group = allGroups.find(groupType);
            System.out.println(groupType);
            System.out.println(group.getParameter());
            while (true) {
                int choice = chooseMenu(new String[]{
                        "Minimum Spent Time",
                        "Minimum Total Pay",
                        "Back"});
                if (choice == 1) {
                    setMinimumSpentTime(group);
                    continue;
                }
                if (choice == 2) {
                    setMinimumTotalPay(group);
                    continue;
                }
                if (choice == 3)
                    break;
            }
        }
    }

}