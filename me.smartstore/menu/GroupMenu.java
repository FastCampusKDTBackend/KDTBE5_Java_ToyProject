package menu;

import group.Groups;
import customer.Customers;
import exception.InputEndException;
import exception.InputRangeException;
import group.Group;
import group.GroupType;
import group.Parameter;
import util.Message;
public class GroupMenu implements Menu {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
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
            int choice = chooseMenu(new String[]{"Set Parameter", "View Parameter", "Update Parameter", "Back"});

            if (choice == 1) {
                setParameter();
            } else if (choice == 2) {
                viewParameter();
            } else if (choice == 3) {
                updateParameter();
            } else {
                break;
            }
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
                System.out.println("Enter minimum usage time");
                int minUsageTime;
                while (true) {
                    try {
                        minUsageTime = Integer.parseInt(nextLine());
                        if (minUsageTime < 0) {
                            throw new InputRangeException();
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    } catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                    }
                }
                parameter.setMinTime(minUsageTime);

                System.out.println("Enter minimum payment amount");
                int minPaymentAmount;
                while (true) {
                    try {
                        minPaymentAmount = Integer.parseInt(nextLine());
                        if (minPaymentAmount < 0) {
                            throw new InputRangeException();
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    } catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                    }
                }
                parameter.setMinPay(minPaymentAmount);

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
            Group group = allGroups.find(groupType);
            if (group == null || group.getParameter() == null) {
                System.out.println("\n" + groupType + " group does not exist.");
            } else {
                System.out.println("\n" + group.getParameter());
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
                System.out.println("\n" + groupType + " group does not exist.");
            } else {
                Parameter parameter = group.getParameter();
                System.out.println("\nCurrent parameter:");
                System.out.println(parameter);

                System.out.println("Enter new minimum usage time (current: " + parameter.getMinTime() + ")");
                int minUsageTime;
                while (true) {
                    try {
                        minUsageTime = Integer.parseInt(nextLine());
                        if (minUsageTime < 0) {
                            throw new InputRangeException();
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    } catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                    }
                }
                parameter.setMinTime(minUsageTime);

                System.out.println("Enter new minimum payment amount (current: " + parameter.getMinPay() + ")");
                int minPaymentAmount;
                while (true) {
                    try {
                        minPaymentAmount = Integer.parseInt(nextLine());
                        if (minPaymentAmount < 0) {
                            throw new InputRangeException();
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    } catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                    }
                }
                parameter.setMinPay(minPaymentAmount);

                System.out.println("\nNew parameter:");
                System.out.println(parameter);

                allCustomers.refresh(allGroups);
            }
        }
    }
    public interface Menu {
        void manage();
    }
}