package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;

import java.util.InputMismatchException;

public class GroupMenu implements Menu {

    private final Groups allGroups;
    private final Customers allCustomers;

    private static GroupMenu groupMenu;

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

    /**
     * 고객 등급별 파라미터를 설정하는 함수
     * 메인메뉴에서 Parameter를 입력했을 때 호출
     */
    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[] {
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});
            if (choice == 1) setParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else break; // Back
        }
    }

    /**
     * 설정하려는 등급에 해당하는 GroupType을 사용자가 입력하면, 그 등급을 GroupType으로 반환함.
     * @return : 사용자가 선택한 GroupType
     *
     */
    public GroupType chooseGroup() {
        while (true) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                GroupType groupType = GroupType.valueOf(choice).replaceFullName();
                return groupType;
            } catch (InputEndException e) {
                System.out.println("**");
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
        return null;
    }

    /**
     * setParameter()
     * @return : void
     * 최초 그룹 파라미터를 초기화 해주는 메서드
     */
    public void setParameter() {
        while (true) {
            try {
                //GroupType groupType = chooseGroup();
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                GroupType groupType = GroupType.valueOf(choice).replaceFullName();

                System.out.println("groupType : " + groupType);
                Group group = allGroups.find(groupType);
                System.out.println("group : " + group);
                if (group != null && group.getParameter() != null) {
                    // 즉, 이미 초기화됨
                    System.out.println("\n" + group.getGroupType() + " group already exists.");
                } else {
                    // 파라미터가 초기화 되지 않음, 여기까지 오면
                    Parameter parameter = new Parameter();
                    makeParameter(parameter);
                    group = new Group(parameter, groupType);
                    allGroups.add(group);
                    allCustomers.refresh();
                    //group.setParameter(parameter);
                    //allCustomers.refresh();
                }
                if (groupType==null) {
                    System.out.println("null");
                    throw new IllegalArgumentException();
                }
                System.out.println(String.format("\nGroupType : %s", groupType.name()));
                System.out.println("Parameter : " + group.getParameter());
            } catch (InputEndException e) {
                System.out.println(Message.END_MSG);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void makeParameter(Parameter parameter) {
        while (true) {
            int choice = chooseMenu(new String[] {
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"});
            if (choice==1) { // minimum spent time setting
                while (true) {
                    try {
                        System.out.println("\nInput Minimum Spent Time");
                        Integer minTime = Integer.parseInt(nextLine()); // 재검토
                        if (minTime<0) throw new InputRangeException();
                        parameter.setMinTime(minTime);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                    } catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                    }
                }
            } else if (choice==2) { // minimum total pay
                while (true) {
                    try {
                        System.out.println("\nInput Minimum Total Pay");
                        Integer minPay = Integer.parseInt(nextLine());
                        if (minPay<0) throw new InputRangeException();
                        parameter.setMinPay(minPay);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                    }
                }
            } else { // back
                break;
            }
        }

    }


    /**
     * 설정한 파라미터를 보여주는 메서드
     */
    public void viewParameter() {
        while (true) {
            try {
                System.out.println("\nWhich group (GENERAL (G), VIP (V), VVIP (VV))?");
                String data = nextLine(Message.END_MSG);
                GroupType groupType = GroupType.valueOf(data).replaceFullName();
                Group group = allGroups.find(groupType);
                System.out.println("group test : " + group);

                System.out.println(String.format("\nGroupType : %s", groupType.name()));
                System.out.println("Parameter : " + (group != null ? group.getParameter() : null));
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }

    /**
     * 설정한 파라미터를 업데이트 하는 메서드
     */
    public void updateParameter() {
        while (true) {
            try {
                System.out.println("\nWhich group (GENERAL (G), VIP (V), VVIP (VV))?");
                String data = nextLine(Message.END_MSG);
                GroupType groupType = GroupType.valueOf(data).replaceFullName();
                Group group = allGroups.find(groupType);

                if (group == null) {
                    System.out.println("No parameter. Set the parameter first.");
                    break;
                } else {
                    Parameter parameter = group.getParameter();
                    makeParameter(parameter); // parameter 클래스의 필드 내용 변경
                    group.setParameter(parameter); // group 클래스의 parameter 필드 변경
                    allCustomers.refresh();
//                    Parameter parameter = new Parameter();
//                    makeParameter(parameter);
//                    group = new Group(parameter, groupType);
//                    allGroups.add(group);
//                    allCustomers.refresh();
                }

            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
}
