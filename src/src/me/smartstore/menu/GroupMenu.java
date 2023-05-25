package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;

import java.util.InputMismatchException;
import java.util.Optional;

import static me.smartstore.util.Message.*;


public class GroupMenu implements Menu{
    private static GroupMenu groupMenu;
    private final Customers allcustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();


    public static GroupMenu getInstance(){
        if(groupMenu == null){
            return groupMenu = new GroupMenu();
        }
        else {
            return groupMenu;
        }
    }

    private GroupMenu(){};

    @Override
    public void manage() {
        while ( true ) {
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) {
                createParameter();
            }
            else if (choice == 2) {
                viewParameter();
            }
            else if (choice == 3) {
                updateParameter();
            }
            else break; // choice == 4
        }
    }
    private void createParameter() {
        while (true) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))?");
                String inputData = nextLine(END_MSG);
                GroupType groupType = GroupType.valueOf(inputData).replaceFullName();
                Optional<Group> foundGroup = allGroups.find(groupType);

                if (foundGroup.isPresent()) {
                    Group group = foundGroup.get();
                    if (group.getParameter() != null) {
                        System.out.println("Group already exists.");
                    } else {
                        Parameter parameter = new Parameter();
                        setParameter(parameter);
                        group = new Group(parameter, groupType);
                        allGroups.add(group);
                        allcustomers.refresh();
                    }
                } else {
                    Parameter parameter = new Parameter();
                    setParameter(parameter);
                    Group group = new Group(parameter, groupType);
                    allGroups.add(group);
                    allcustomers.refresh();
                }
            } catch (InputEndException e) {
                System.out.println(ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
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
                        System.out.println("Input Minimum Spent Time: ");
                        Integer minTime = formatInt(nextLine(END_MSG));
                        parameter.setMinTime(minTime);
                        break;
                    }

                    catch (InputRangeException e) {
                        System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
                    }

                    catch (InputMismatchException e) {
                        System.out.println(ERR_MSG_INVALID_INPUT_FORMAT);
                    }
                }
            } else if (choice == 2) {
                while (true) {
                    try {
                        System.out.println("Input Minimum Total Pay: ");
                        Integer minPay = formatInt(nextLine(END_MSG));
                        parameter.setMinPay(minPay);
                        break;
                    }

                    catch (InputRangeException e) {
                        System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
                    }

                    catch (InputMismatchException e) {
                        System.out.println(ERR_MSG_INVALID_INPUT_FORMAT);
                    }
                }
            } else {
                break;
            }
        }
    }


    private void viewParameter() {
        while (true) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))?");
                String inputData = nextLine(END_MSG);
                GroupType groupType = GroupType.valueOf(inputData).replaceFullName();
                Optional<Group> foundGroup = allGroups.find(groupType);

                if (foundGroup.isPresent()) {
                    Group group = foundGroup.get();
                    System.out.printf("%nGroupType: %s%n", groupType.name());
                    System.out.println("Parameter: " + group.getParameter());
                } else {
                    System.out.println("Group not found.");
                }
            } catch (InputEndException e) {
                System.out.println(ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
    private void updateParameter() {
        while (true) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))?");
                String inputData = nextLine(END_MSG);
                GroupType groupType = GroupType.valueOf(inputData).replaceFullName();
                Optional<Group> foundGroup = allGroups.find(groupType);

                if (foundGroup.isPresent()) {
                    Group group = foundGroup.get();
                    Parameter parameter = group.getParameter();
                    setParameter(parameter);
                    group.setParameter(parameter);
                    allcustomers.refresh();
                } else {
                    System.out.println("Group not found.");
                    break;
                }
            } catch (InputEndException e) {
                System.out.println(ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

}
