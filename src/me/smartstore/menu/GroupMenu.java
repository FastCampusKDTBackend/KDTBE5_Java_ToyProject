package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.GroupConditions;
import me.smartstore.utils.Message;

public class GroupMenu implements Menu{

    private static GroupMenu groupMenu;

    private final Groups allGroups;
    private final Customers allCustomers;

    public static GroupMenu getInstance() {
        if(groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {
        this.allGroups = Groups.getInstance();
        this.allCustomers = Customers.getInstance();
    }

    @Override
    public void manage() {
        while ( true ) {
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) setParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else MainMenu.getInstance().manage();
        }
    }

    public GroupType chooseGroup() {
        while ( true ) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                return GroupType.valueOf(choice).replaceFullName();
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public void setParameter() {
        while ( true ) {
            GroupType groupType = chooseGroup();

            if (groupType == null) manage();
            else if (groupType == GroupType.NONE) {
                System.out.println(Message.ERR_MSG_INPUT_NONE);
                manage();
            }

            Group group = allGroups.findGroupByGroupType(groupType);
            if (group != null && group.getParameter() != null) {
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                inputTimeAndPay(groupType);
                allCustomers.refresh(allGroups);
            }
        }
    }

    public void inputTimeAndPay(GroupType groupType) {

        Group group = null;

        int minimumTime = 0;
        int minimumPay = 0;

        if (allGroups.findGroupByGroupType(groupType) != null) {
            group = allGroups.findGroupByGroupType(groupType);
            minimumTime = group.getParameter().getMinTime();
            minimumPay = group.getParameter().getMinPay();
        } else {
            allGroups.add(new Group(new GroupConditions(null, null), groupType));
        }

        while ( true ) {

            int choice = chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"});

            if (choice == 1) minimumTime = setMinimumTime();
            else if (choice == 2) minimumPay = setMinimumPay();
            else {
                System.out.println("\n" + group);
                manage();
            }

            group = allGroups.findGroupByGroupType(groupType);
            group.setParameter(new GroupConditions(minimumTime, minimumPay));
            allGroups.set(allGroups.indexOf(group), group);

            allCustomers.refresh(allGroups);
        }
    }

    public void viewParameter() {
        while ( true ) {
            GroupType groupType = chooseGroup();

            if (groupType == null) manage();

            Group group = allGroups.findGroupByGroupType(groupType);

            if (group != null && group.getParameter() != null) {
                System.out.println(group);
            } else if (group == null){
                System.out.println("GroupType: " + groupType);
                System.out.println("Parameter: " + null);
            }
        }
    }

    public int setMinimumTime() {
        while ( true ) {
            try {
                System.out.println("Input Minimum Spent Time: ");
                String minTime = nextLine(Message.END_MSG);
                return Integer.parseInt(minTime);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public int setMinimumPay() {
        while ( true ) {
            try {
                System.out.println("Input Minimum Total Pay : ");
                String minPay = nextLine(Message.END_MSG);
                return Integer.parseInt(minPay);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public void updateParameter() {
        while ( true ) {
            GroupType groupType = chooseGroup();

            if (groupType == null) manage();
            else if (groupType == GroupType.NONE) {
                System.out.println(Message.ERR_MSG_INPUT_NONE);
                manage();
            }

            Group group = allGroups.findGroupByGroupType(groupType);

            if (group != null && group.getParameter() != null) {
                System.out.println("\n" + group);
                inputTimeAndPay(groupType);
            } else {
                System.out.println(Message.ERR_MSG_INVALID_PARAMETER_ARR_EMPTY);
                manage();
            }
        }
    }
}
