package me.smartstore.domain.menu;

import me.smartstore.domain.group.Group;
import me.smartstore.domain.group.GroupType;
import me.smartstore.domain.group.Groups;
import me.smartstore.domain.group.Parameter;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

import java.util.InputMismatchException;

import static me.smartstore.exception.message.ErrorMessage.ERR_MSG_INVALID_INPUT_RANGE;
import static me.smartstore.exception.message.ErrorMessage.ERR_MSG_INVALID_INPUT_TYPE;

public class GroupMenu implements Menu {
    private static final Integer GROUP_TYPE_COUNT = 3;
    private static GroupMenu groupMenu;
    private final Groups allGroups;

    private final boolean[] isExitType;
    private final Parameter[] parameters;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {
        isExitType = new boolean[GROUP_TYPE_COUNT];
        parameters = new Parameter[GROUP_TYPE_COUNT];
        this.allGroups = Groups.getInstance();
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
                String inputData = nextLine("END");
                GroupType groupType = GroupType.valueOf(inputData).convertToFullName();

                // 존재하는지 확인
                Integer typeIndex = GroupType.getTypeIndex(groupType);
                if (isExitType[typeIndex]) {
                    System.out.println(String.format("%s group already exists.", groupType.name()));
                } else {
                    Parameter parameter = new Parameter();
                    setParameter(parameter);
                    parameters[typeIndex] = parameter;
                    isExitType[typeIndex] = true;
                    Group group = allGroups.get(typeIndex + 1);
                    group.setParameter(parameter);
                    group.setGroupType(groupType);
                    System.out.println(allGroups);
                }
                System.out.println(String.format("\nGroupType: %s", groupType.name()));
                System.out.println("Parameter: " + parameters[typeIndex]);

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
                String inputData = nextLine("END");
                GroupType groupType = GroupType.valueOf(inputData).convertToFullName();
                Integer typeIndex = GroupType.getTypeIndex(groupType);

                System.out.println(String.format("\nGroupType: %s", groupType.name()));
                System.out.println("Parameter: " + parameters[typeIndex]);
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
                String inputData = nextLine("END");
                GroupType groupType = GroupType.valueOf(inputData).convertToFullName();

                // 존재하는지 확인
                Integer typeIndex = GroupType.getTypeIndex(groupType);
                if (!isExitType[typeIndex]) {
                    System.out.println("No parameter. Set the parameter first.");
                    break;
                } else {
                    Parameter parameter = parameters[typeIndex];
                    setParameter(parameter);
                    allGroups.get(typeIndex + 1).setParameter(parameter);
                    System.out.println(allGroups);
                }
                System.out.println(String.format("\nGroupType: %s", groupType.name()));
                System.out.println("Parameter: " + parameters[typeIndex]);

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
                        Integer minUsageTime = convertInt(nextLine("END"));
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
                        Integer minPurchaseAmount = convertInt(nextLine("END"));
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
