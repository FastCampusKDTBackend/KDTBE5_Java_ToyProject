package jyjang.smartstore.menu;

import jyjang.smartstore.customer.Customers;
import jyjang.smartstore.exception.InputEndException;
import jyjang.smartstore.exception.InputRangeException;
import jyjang.smartstore.group.Group;
import jyjang.smartstore.group.GroupType;
import jyjang.smartstore.group.Groups;
import jyjang.smartstore.group.Parameter;
import jyjang.smartstore.util.Message;

public class GroupMenu implements Menu {

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null)
            groupMenu = new GroupMenu();
        return groupMenu;
    }

    private GroupMenu() {

    }

    @Override
    public void manage() {
        while (true) {
            int choice = displayMenu(new String[] {
                    "Set Parameter"
                    , "View Parameter"
                    , "Update Parameter"
                    , "Back"});

            if (choice == 1) {
                setParameter();
            } else if (choice == 2) {
                viewParameter();
            } else if (choice == 3) {
                updateParameter();
            } else if (choice == 4) {
                break;
            }
        }
    }

    public GroupType chooseGroup() {
        while (true) {
            try {
                System.out.println("Which group (GENERAL (G),  VIP (V), VVIP (VV))? ");
                // 프로그램을 끝내기 위한 신호기 때문에 내부 메소드에 입력 23.04.27
                String choice = nextLine(Message.END_MSG);

                GroupType groupType = GroupType.getGroupTypeByInitial(choice);
                return groupType;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    private void setParameter() {
        while (true) {
            GroupType groupType = chooseGroup();

            Group group = allGroups.find(groupType);

            if (group != null && group.getParameter() != null) {
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                Parameter parameter = new Parameter();

                while (true) {
                    int choice = displayMenu(new String[] {
                            "Minimum Spent Time"
                            , "Minimum Total Pay"
                            , "Back" });
                    if (choice == 1) {
                        setParameterMinimumSpentTime(parameter);
                        continue;
                    }
                    if (choice == 2) {
                        setParameterMinimumTotalPay(parameter);
                        continue;
                    }
                    if (choice == 3)
                        break;
                }

                allGroups.add(new Group(groupType, parameter));
                allCustomers.refresh();
            }
        }
    }

    private void viewParameter() {
    }

    private void updateParameter() {
    }

    private void setParameterMinimumSpentTime(Parameter parameter) {
        while (true) {
            try {
                System.out.println();
                System.out.println("Input Minimum Spent Time : ");

                int minimumSpentTime = Integer.parseInt(nextLine("end"));
                if (minimumSpentTime < 0) {
                    throw new InputRangeException();
                }

                parameter.setMinimumSpentTime(minimumSpentTime);
                return;
            } catch (InputRangeException e) {
                System.out.println();
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            } catch (InputEndException e) {
                System.out.println();
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }

    private void setParameterMinimumTotalPay(Parameter parameter) {
        while (true) {
            try {
                System.out.println();
                System.out.println("Input Minimum Spent Time : ");

                int minimumTotalPay = Integer.parseInt(nextLine("end"));
                if (minimumTotalPay < 0) {
                    throw new InputRangeException();
                }

                parameter.setMinimumTotalPay(minimumTotalPay);
                return;
            } catch (InputRangeException e) {
                System.out.println();
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            } catch (InputEndException e) {
                System.out.println();
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }

}

