package me.smartstore.domain.menu;

import me.smartstore.domain.customer.Customers;
import me.smartstore.domain.group.Group;
import me.smartstore.domain.group.constant.GroupType;
import me.smartstore.domain.group.Groups;
import me.smartstore.domain.group.Parameter;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

import java.util.InputMismatchException;

import static me.smartstore.exception.constant.Message.*;
import static me.smartstore.exception.constant.Message.END_MSG;

public class GroupMenu implements Menu {
    private static GroupMenu groupMenu;
    private final Groups allGroups;
    private final Customers allCustomers;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
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
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"
            });

            if (choice == 1) {
                createParameter();
            } else if (choice == 2) {
                findParameter();
            } else if (choice == 3) {
                updateParameter();
            } else {
                break;
            }
        }
    }

    private void createParameter() {
        while (true) {
            try {
                System.out.println("\nWhich group (GENERAL (G), VIP (V), VVIP (VV))?");
                String inputData = nextLine(END_MSG.getMessage());
                GroupType groupType = GroupType.valueOf(inputData).convertToFullName();
                Group group = allGroups.find(groupType);

                if (group != null && group.getParameter() != null) {
                    System.out.println(String.format("%s group already exists.", groupType.name()));
                } else {
                    Parameter parameter = new Parameter();
                    setParameter(parameter);
                    group = new Group(parameter, groupType);
                    allGroups.add(group);
                    allCustomers.refresh();
                }
                System.out.println(String.format("\nGroupType: %s", groupType.name()));
                System.out.println("Parameter: " + group.getParameter());

            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void findParameter() {
        while (true) {
            try {
                System.out.println("\nWhich group (GENERAL (G), VIP (V), VVIP (VV))?");
                String inputData = nextLine(END_MSG.getMessage());
                GroupType groupType = GroupType.valueOf(inputData).convertToFullName();
                Group group = allGroups.find(groupType);

                System.out.println(String.format("\nGroupType: %s", groupType.name()));
                System.out.println("Parameter: " + (group != null ? group.getParameter() : null));
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void updateParameter() {
        while (true) {
            try {
                System.out.println("\nWhich group (GENERAL (G), VIP (V), VVIP (VV))?");
                String inputData = nextLine(END_MSG.getMessage());
                GroupType groupType = GroupType.valueOf(inputData).convertToFullName();
                Group group = allGroups.find(groupType);

                if (group == null) {
                    System.out.println("No parameter. Set the parameter first.");
                    break;
                } else {
                    Parameter parameter = group.getParameter();
                    setParameter(parameter);
                    group.setParameter(parameter);
                    allCustomers.refresh();
                }
                System.out.println(String.format("\nGroupType: %s", groupType.name()));
                System.out.println("Parameter: " + group.getParameter());

            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setParameter(Parameter parameter) {
        while (true) {
            int choice = chooseMenu(new String[] {
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"
            });

            if (choice == 1) {
                while (true) {
                    try {
                        System.out.println("\nInput Minimum Spent Time:");
                        Integer minUsageTime = convertInt(nextLine(END_MSG.getMessage()));
                        if (minUsageTime < 0) {
                            throw new InputRangeException();
                        }
                        parameter.setMinUsageTime(minUsageTime);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(ERR_MSG_INVALID_INPUT_TYPE.getMessage());
                    } catch (InputRangeException e) {
                        System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
                    }
                }
            } else if (choice == 2) {
                while (true) {
                    try {
                        System.out.println("\nInput Minimum Total Pay:");
                        Integer minPurchaseAmount = convertInt(nextLine(END_MSG.getMessage()));
                        if (minPurchaseAmount < 0) {
                            throw new InputRangeException();
                        }
                        parameter.setMinPurchaseAmount(minPurchaseAmount);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(ERR_MSG_INVALID_INPUT_TYPE.getMessage());
                    } catch (InputRangeException e) {
                        System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
                    }
                }
            } else {
                break;
            }
        }
    }
}
