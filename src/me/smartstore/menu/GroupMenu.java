package me.smartstore.menu;

import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.exception.constant.Message;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

import me.smartstore.customer.Customers;
import me.smartstore.group.Parameter;

public class GroupMenu implements Menu{
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    @Override
    public void manage(){
        while (true) {
            try {
                int choice = chooseMenu(new String[]{
                        "Set Parameter",
                        "View Parameter",
                        "Update Parameter",
                        "Back"
                });

                if (choice == 1) {
                    setParameter();
                } else if (choice == 2) {
                    viewParameter();
                } else if (choice == 3) {
                    updateParameter();
                } else {
                    break;
                }
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT.getMessage());
            }
        }
    }

    public void setParameter() {
        while (true) {
            try {
                System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String data = nextLine("END");
                GroupType groupType = GroupType.valueOf(data).replaceFullName();
                Group group = allGroups.find(groupType);

                if (group != null && group.getParameter() != null) {
                    System.out.println("\n" + group.getGroupType() + " group already exists.\n");
                    System.out.println("GroupType: " + group.getGroupType());
                    System.out.println("Parameter: " + group.getParameter());
                }
                else {
                    Parameter parameter = new Parameter();

                    while (true) {
                        int choice = chooseMenu(new String[]{
                            "Minimum Spent Time",
                            "Minimum Total Pay",
                            "Back"
                        });

                        if (choice == 1) {
                            setParameterMinTime(parameter);
                        } else if (choice == 2) {
                            setParameterMinTotalPay(parameter);
                        } else if (choice == 3) {
                            break;
                        }
                    }

                    allGroups.add(new Group(parameter, groupType));
                    allCustomers.refresh();
                    System.out.println();
                    System.out.println("GroupType: " + groupType);
                    System.out.println("Parameter: " + parameter);
                }
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
                break;
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            }
        }
    }

    public void viewParameter() {
        while (true) {
            try {
                System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String data = nextLine("END");
                GroupType groupType = GroupType.valueOf(data).replaceFullName();
                Group group = allGroups.find(groupType);

                System.out.println();
                System.out.println("GroupType: " + groupType);

                if (group == null)
                    System.out.println("Parameter: " + null);
                else
                    System.out.println("Parameter: " + group.getParameter());

            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
                break;
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            }
        }
    }

    public void updateParameter() {
        while (true) {
            try {
                System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String data = nextLine("END");
                GroupType groupType = GroupType.valueOf(data).replaceFullName();
                Group group = allGroups.find(groupType);

                if (group == null) {
                    System.out.println("No parameter. Set the parameter first.");
                    break;
                }

                System.out.println();
                System.out.println("GroupType: " + groupType);
                System.out.println("Parameter: " + group.getParameter());

                Parameter parameter = group.getParameter();

                while (true) {
                    int choice = chooseMenu(new String[]{
                            "Minimum Spent Time",
                            "Minimum Total Pay",
                            "Back"
                    });

                    if (choice == 1) {
                        setParameterMinTime(parameter);
                    } else if (choice == 2) {
                        setParameterMinTotalPay(parameter);
                    } else if (choice == 3) {
                        System.out.println();
                        System.out.println("GroupType: " + groupType);
                        System.out.println("Parameter: " + group.getParameter());
                        break;
                    }

                    this.allGroups.update(new Group(parameter, groupType));
                    this.allCustomers.refresh();
            }
        } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
                break;
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            }
        }
    }

    public void setParameterMinTime(Parameter parameter) {
        while (true) {
            try{
                System.out.println("\nInput Minimum Spent Time: ");
                int minSpentTime = Integer.parseInt(nextLine("END"));

                if (minSpentTime < 0) {
                    throw new InputRangeException();
                }

                parameter.setMinTime(Integer.valueOf(minSpentTime));
                return;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE.getMessage());
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
                break;
            }
        }
    }

    public void setParameterMinTotalPay(Parameter parameter) {
        while (true) {
            try{
                System.out.println("\nInput Minimum Total Pay: ");
                int minTotalPay = Integer.parseInt(nextLine("END"));

                if (minTotalPay < 0) {
                    throw new InputRangeException();
                }

                parameter.setMinPay(Integer.valueOf(minTotalPay));
                return;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE.getMessage());
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
                break;
            }
        }
    }
}