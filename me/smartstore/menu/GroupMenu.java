package smartstore.menu;

import smartstore.exception.InputEndException;
import smartstore.group.Group;
import smartstore.group.GroupType;
import smartstore.group.Groups;
import smartstore.group.Parameter;
import smartstore.util.Message;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GroupMenu implements Menu{

    private final Groups groups = Groups.getInstance();

    private static final GroupMenu INSTANCE = new GroupMenu();

    public static GroupMenu getInstance() {
        return INSTANCE;
    }

    private GroupMenu(){

    }

    @Override
    public void manage() {
        while(true) {
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"
            });
            if(choice == 1) setParameter();
            else if(choice == 2) viewParameter();
            else if(choice == 3) updateParameter();
            else break;
        }
    }

    private void setParameter() {
        while(true) {
            GroupType groupType = chooseGroup();

            if(groupType == null) {
                break;
            }

            Scanner sc = new Scanner(System.in);
            Group group = groups.findGroup(groupType);
            if(group != null && group.getParameter() != null) {
                System.out.println(group.getGroupType() + " group already exists.");
                System.out.println("Group Type : " + group.getGroupType());
                System.out.println("Parameter : " + group.getParameter());
            }else {
                Group addGroup = new Group();
                Parameter parameter = new Parameter();
                while (true) {
                    int choice = chooseMenu(new String[]{
                            "Minimum Spent Time",
                            "Minimum Total Pay",
                            "Back"
                    });
                    if(choice == 1) {
                        try {
                            System.out.print("Input Minimum Spent Time: ");
                            int min = sc.nextInt();
                            parameter.setMinTime(min);
                        }catch (InputMismatchException e) {
                            System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                        }
                    }else if(choice == 2) {
                        try {
                            System.out.print("Input Minimum Total Pay: ");
                            int pay = sc.nextInt();
                            parameter.setMinPay(pay);
                        }catch (InputMismatchException e) {
                            System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                        }
                    }else {
                        addGroup.setParameter(parameter);
                        addGroup.setGroupType(groupType);
                        groups.getGroupSArray().add(addGroup);
                        break;
                    }
                }
            }
        }
    }

//    private Group findGroup(GroupType groupType) {
//        for(int i = 0; i < groups.getGroupSArray().size(); i++) {
//            if(groups.getGroupSArray().get(i).getGroupType() == groupType){
//                return groups.getGroupSArray().get(i);
//            }
//        }
//        return null;
//    }

    private GroupType chooseGroup() {
        while(true) {
            try {
                System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);

                GroupType groupType = GroupType.valueOf(choice).replaceFullName();

                return groupType;
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            }catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }

        }
    }

    private void viewParameter() {
        while(true) {
            GroupType groupType = chooseGroup();
            if(groupType == null) {
                break;
            }
            Group group = groups.findGroup(groupType);
            if(group != null) {
                System.out.println("Group Type : " + group.getGroupType());
                System.out.println("Parameter : " + group.getParameter());
            }else {
                System.out.println(group);
            }
        }
    }

    private void updateParameter() {
        while(true) {
            GroupType groupType = chooseGroup();
            if(groupType == null) {
                break;
            }
            Group group = groups.findGroup(groupType);

            if(group == null) {
                System.out.println("No parameter. Set the parameter first.");
                break;
            }

            Scanner sc = new Scanner(System.in);
            if(group != null && group.getParameter() != null) {
                Parameter parameter = new Parameter();
                while (true) {
                    int choice = chooseMenu(new String[]{
                            "Minimum Spent Time",
                            "Minimum Total Pay",
                            "Back"
                    });
                    if (choice == 1) {
                        try {
                            System.out.print("Input Minimum Spent Time: ");
                            int min = sc.nextInt();
                            parameter.setMinTime(min);
                        } catch (InputMismatchException e) {
                            System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                        }
                    } else if (choice == 2) {
                        try {
                            System.out.print("Input Minimum Total Pay: ");
                            int pay = sc.nextInt();
                            parameter.setMinPay(pay);
                        } catch (InputMismatchException e) {
                            System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                        }
                    } else {
                        group.setParameter(parameter);
                        break;
                    }
                }
            }
        }
    }

}
