package menu;

import exception.InputEndException;
import exception.InputRangeException;
import group.Group;
import group.GroupType;
import group.Parameter;
import util.Message;
import group.Groups;
import customer.Customers;

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

    private GroupMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) {
                setParameter();
            } else if (choice == 2) {
                viewParameter();
            } else if (choice == 3) {
                updateParameter();
            }
            else {
                break; // choice == 4
            }
        }
    }

    public GroupType chooseGroup() {
        while (true) {
            try {
                System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                System.out.println();
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



    public void setParameter() { // 초기화할 때만 호출 가능
        while ( true ) {
            GroupType groupType = chooseGroup();
            if (groupType == null) break;

            // GroupType에 해당하는 group 객체를 찾아야 함
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                Parameter parameter = new Parameter();
                this.setDetailParameter(parameter);
                group = new Group(parameter, groupType);
                // time, pay 사용자 입력받은 후, 설정 필요
                group.setParameter(parameter);

                allGroups.add(group);
                allCustomers.refresh(allGroups); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
            }
        }

    }

    /*
        allGroups.add(new Group(new Parameter(10, 100000), GroupType.GENERAL));
        allGroups.add(new Group(new Parameter(20, 200000), GroupType.VIP));
        allGroups.add(new Group(new Parameter(30, 300000), GroupType.VVIP));
     */
    public void setDetailParameter(Parameter parameter) {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"});

            if (choice == 1) {
                // Minimum Spent Time
                System.out.println("Input Minimum Spent Time: ");
                Integer minTime = Integer.parseInt(nextLine(Message.END_MSG));
                parameter.setMinTime(minTime);
            }

            else if (choice == 2) {
                // Minimum Total Pay
                System.out.println("Input Minimum Total Pay: ");
                Integer minPay = Integer.parseInt(nextLine(Message.END_MSG));
                parameter.setMinPay(minPay);

            }

            else break; // choice == 3
        }
    }

    public void viewParameter() {
        while (true) {
            GroupType groupType = chooseGroup();
            if (groupType == null) break;

            // GroupType에 해당하는 group 객체를 찾아야 함
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) {
                System.out.println("GroupType: " + group.getGroupType() + "\n" +
                                   "Parameter: " + group.getParameter());
            } else if (group == null) {
                System.out.println("GroupType: " + groupType + "\n" +
                        "Parameter: " + new Parameter());
            }
        }
    }

    public void updateParameter() {
        while ( true ) {
            GroupType groupType = chooseGroup();
            if (groupType == null) break;

            // GroupType에 해당하는 group 객체를 찾아야 함

            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) {
                System.out.println("GroupType: " + group.getGroupType() + "\n" +
                        "Parameter: " + group.getParameter());
                setDetailParameter(group.getParameter());
                int index = allGroups.indexOf(group);
                allGroups.set(index, group);
            } else if (group == null) {
                System.out.println("GroupType: " + groupType + "\n" +
                        "Parameter: " + new Parameter());

                Parameter parameter = new Parameter();
                this.setDetailParameter(parameter);
                group = new Group(parameter, groupType);
                // time, pay 사용자 입력받은 후, 설정 필요
                group.setParameter(parameter);
                allGroups.add(group);
            }

            allCustomers.refresh(allGroups); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
            }
        }
    }


