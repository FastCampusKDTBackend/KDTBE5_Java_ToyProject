package me.smartstore.menu;


import me.smartstore.customer.Customers;
import me.smartstore.exception.EmptyArrayException;
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

    private GroupMenu() {
    }

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) setParameter();
            else if (choice == 2) {
                viewParameter();
            } else if (choice == 3) {
                updateParameter();
            } else break;
        }
    }

    public GroupType chooseGroup() {
        while (true) {
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


    public void setParameter() {
        while (true) {
            GroupType groupType = chooseGroup();
            if (groupType == null) {
                break;
            }
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) {
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                Parameter parameter = new Parameter();
                System.out.println("Enter min time");
                int time;
                while (true) {
                    try {
                        time = Integer.parseInt(nextLine());
                        if (time < 0) throw new InputRangeException();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    } catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                    }
                }
                parameter.setMinTime(time);

                System.out.println("Enter min pay");
                int pay;
                while (true) {
                    try {
                        pay = Integer.parseInt(nextLine());
                        if (pay < 0) throw new InputRangeException();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    } catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                    }
                }
                parameter.setMinPay(pay);

                group.setParameter(parameter);
                allCustomers.refresh(allGroups);
            }
        }
    }

    public void viewParameter() {
        while (true) {
            GroupType groupType = chooseGroup();
            if (groupType == null) {
                break;
            }
            try {
                Group group = allGroups.find(groupType);
                System.out.println(groupType);
                System.out.println(group.getParameter());
            } catch (EmptyArrayException e) {
                System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
            }
        }
    }

    public void updateParameter() {
        while (true) {
            GroupType groupType = chooseGroup();
            if (groupType == null) {
                break;
            }

            Group group = allGroups.find(groupType);
            if (group == null || group.getParameter() == null) {
                System.out.println("\n" + groupType + " group does not exist or parameter is not set.");
            } else {
                System.out.println("\nCurrent parameter: " + group.getParameter());

                while (true) {
                    try {
                        System.out.print("Enter minimum time \n");
                        int minTime = Integer.parseInt(nextLine(Message.END_MSG));

                        System.out.print("Enter minimum pay \n");
                        int minPay = Integer.parseInt(nextLine(Message.END_MSG));

                        group.getParameter().setMinTime(minTime);
                        group.getParameter().setMinPay(minPay);

                        allCustomers.refresh(allGroups);

                        System.out.println("\nUpdated parameter: " + group.getParameter());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                    } catch (InputEndException e) {
                        System.out.println(Message.ERR_MSG_INPUT_END);
                        break;
                    }
                }
            }
            allCustomers.refresh(allGroups);
        }
    }
}