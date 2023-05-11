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
    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if(groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {}

    @Override
    public void manage() {
        while(true) {
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

    public GroupType chooseGroup() {
        while(true) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG).toUpperCase();
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

    public void setParameter() {
        while(true) {
            GroupType groupType = chooseGroup();
            if(groupType == null) {
                return;
            }
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) {
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            }
            else {
                Parameter parameter = new Parameter();
                while(true) {
                    int choice = setParameterMenu();
                    if(choice == 1) spentMinParameter(parameter);
                    else if(choice == 2) totalMinParameter(parameter);
                    else break;
                }
                allGroups.add(new Group(parameter, groupType));
                allCustomers.refresh(allGroups);
            }
        }
    }

    public void viewParameter() {
        while(true) {
            GroupType groupType = chooseGroup();
            if(groupType == null) {
                return;
            }
            Group group = allGroups.find(groupType);
            if(group == null) {
                System.out.println("GroupType: " + groupType);
                System.out.println("Parameter: null");
            } else {
                System.out.println("GroupType: " + groupType);
                System.out.println("Parameter: " + group.getParameter());
            }
        }
    }

    public void updateParameter() {
        while(true) {
            GroupType groupType = chooseGroup();
            if(groupType == null) {
                return;
            }
            Group group = allGroups.find(groupType);
            if(group == null) {
                System.out.println("No parameter. Set the parameter first.");
                return;
            }
            System.out.println("GroupType: " + group.getGroupType());
            System.out.println("Parameter: " + group.getParameter());
            Parameter parameter = group.getParameter();
            while(true) {
                int choice = setParameterMenu();
                if(choice == 1) spentMinParameter(parameter);
                else if(choice == 2) totalMinParameter(parameter);
                else break;
            }
        }
    }

    public int setParameterMenu() {
        while(true) {
            try {
                System.out.println("===============================");
                System.out.println(" 1. Minimum Spent Time");
                System.out.println(" 2. Minimum Total Pay");
                System.out.println(" 3. Back");
                System.out.println("===============================");
                System.out.print("Choose One: ");
                int choice = Integer.parseInt(nextLine());
                if (choice >= 1 && choice <= 3)
                    return choice;
                throw new InputRangeException();
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public void spentMinParameter(Parameter parameter) {
        while(true) {
            try {
                System.out.println("Input Minimum Spent Time: ");
                int minSpentTime = Integer.parseInt(nextLine(Message.END_MSG));
                parameter.setMinTime(minSpentTime);
                allCustomers.refresh(allGroups);
                return;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public void totalMinParameter(Parameter parameter) {
        while(true) {
            try {
                System.out.println("Input Minimum Total Pay: ");
                int minTotalPay = Integer.parseInt(nextLine(Message.END_MSG));
                parameter.setMinPay(minTotalPay);
                allCustomers.refresh(allGroups);
                return;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
}
