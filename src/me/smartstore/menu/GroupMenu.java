package me.smartstore.menu;

import me.smartstore.customers.Customers;
import me.smartstore.groups.Group;
import me.smartstore.groups.GroupType;
import me.smartstore.groups.Groups;
import me.smartstore.groups.Parameter;
import me.smartstore.util.Board;
import me.smartstore.util.Message;
import me.smartstore.util.exception.InputEndException;
import me.smartstore.util.exception.InputRangeException;
import me.smartstore.util.exception.NullArgumentException;

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
        while ( true ) {
            int choice = chooseMenu(Board.GROUP_MENU.getBoard());

            if (choice == 1) setParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else break;
        }
    }

    /*
    * chooseGroup() 못쓰게 됌 : setParameter()에서 값을 못가져 오는 이슈
    * */

    /*
    * 가장 처음 초기화 할 때
    * 값이 있을 경우
    * @ group(choice) -> GroupType(enum)
    * @ E -> GroupType.E : Error
    * => IllegalArgumentException
    * */
    private void setParameter() {
        while (true) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                GroupType groupType = GroupType.valueOf(choice).replaceFullName();
                Group group = allGroups.find(groupType);

                if (group != null && group.getParmeter() != null) {
                    System.out.println("\n" + group.getGroupType() + " group already exists.");
                } else {
                    Parameter parameter = new Parameter();
                    setParameterValue(parameter, groupType);
                    allCustomers.refresh(allGroups);
                }
                System.out.println("\nGroupType: " + groupType);

                if (group == null) {
                    System.out.println("Parameter: " + group);
                } else {
                    System.out.println("Parameter: " + group.getParmeter());
                }

            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;

            } catch (IllegalArgumentException | InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);

            } catch (NullArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
            }
        }
    }

    private void setParameterValue(Parameter parameter, GroupType groupType) {
        while (true) {

            int choice = chooseMenu(Board.PARAMETER_MENU.getBoard());

            if (choice == 1) {
                while (true) {
                    try {
                        System.out.println("\nInput Minimum Spent Time:");
                        String minSpentTime = nextLine(Message.END_MSG);

                        if (Integer.parseInt(minSpentTime) < 0) {
                            throw new InputRangeException();
                        }

                        parameter.setMinTime(Integer.parseInt(minSpentTime));
//                        System.out.println(parameter);
                        System.out.println();
                        break;

                    } catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                        System.out.println();

                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                        System.out.println();
                    }
                }
            } else if (choice == 2) {
                while (true) {
                    try {
                        System.out.println("\nInput Minimum Total Pay:");
                        String minPayment = nextLine(Message.END_MSG);

                        if (Integer.parseInt(minPayment) < 0) {
                            throw new InputRangeException();
                        }

                        parameter.setMinPayment(Integer.parseInt(minPayment));
//                        System.out.println(parameter);
                        System.out.println();
                        break;

                    } catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                        System.out.println();

                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                        System.out.println();
                    }
                }
            } else {
                if (allGroups.find(groupType) == null) {
                    allGroups.add(new Group(new Parameter(parameter.getMinTime(), parameter.getMinPayment()), groupType));
                }
                break;
            }
        }
    }

    private void viewParameter() {
        while ( true ) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                GroupType groupType = GroupType.valueOf(choice).replaceFullName();
                Group group = allGroups.find(groupType);

                System.out.println("\nGroupType: " + groupType);
                if (group == null) {
                    System.out.println("Parameter: " + group);
                } else {
                    System.out.println("Parameter: " + group.getParmeter());
                }

            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;

            } catch (IllegalArgumentException | InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);

            } catch (NullArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
            }
        }
    }

    private void updateParameter() {
        while ( true ) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                GroupType groupType = GroupType.valueOf(choice).replaceFullName();
                Group group = allGroups.find(groupType);

                if (group == null) {
                    System.out.println("No parameter. Set the parameter first.");
                    break;
                }
                else {
                    Parameter parameter = group.getParmeter();
                    setParameterValue(parameter, groupType);
                    group.setParmeter(parameter);
                    allCustomers.refresh(allGroups);
                }

                System.out.println("\nGroupType: " + groupType);

                if (group == null) {
                    System.out.println("Parameter: " + group);
                } else {
                    System.out.println("Parameter: " + group.getParmeter());
                }
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;

            } catch (IllegalArgumentException | InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);

            } catch (NullArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
            }
        }
    }

}
