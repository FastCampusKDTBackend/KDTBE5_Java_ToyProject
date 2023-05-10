package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.utils.Message;

public class GroupMenu implements Menu{

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if(groupMenu == null) {
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

            if (choice == 1) setParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else MainMenu.getInstance().manage();
        }
    }

    public GroupType chooseGroup() {
        while ( true ) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                return GroupType.valueOf(choice).replaceFullName();
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public void setParameter() { // 초기화할 때만 호출 가능
        while ( true ) {
            GroupType groupType = chooseGroup();

            // GroupType 에 해당하는 group 객체를 찾아야 함
            Group group = allGroups.findGroupByGroupType(groupType);
            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                inputTimeAndPay(groupType);
                // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
                allCustomers.refresh(allGroups);
            }
        }
    }

    public void inputTimeAndPay(GroupType groupType) {

        Group group = null;

        int minimumTime = 0;
        int minimumPay = 0;

        if (allGroups.findGroupByGroupType(groupType) != null) {
            group = allGroups.findGroupByGroupType(groupType);
            minimumTime = group.getParameter().getMinTime();
            minimumPay = group.getParameter().getMinPay();
        } else {
            // add Parameter - 함수 진입 시 해당 groupType 으로 Group 생성
            allGroups.add(new Group(new Parameter(null, null), groupType));
        }

        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while

            int choice = chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"});

            if (choice == 1) minimumTime = setMinimumTime();
            else if (choice == 2) minimumPay = setMinimumPay();
            else {
                System.out.println("\n" + group);
                manage(); // choice == 3
            }

            group = allGroups.findGroupByGroupType(groupType);
            group.setParameter(new Parameter(minimumTime, minimumPay));
            allGroups.set(allGroups.indexOf(group), group);

            // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
            allCustomers.refresh(allGroups);

            // 삭제
            System.out.println("\n" + group);

            // Group 내에 있는 다른 그룹 파라미터와 비교?
            // allGroups.checkGroupParameter(group);
        }
    }

    public void viewParameter() {
        while ( true ) {
            GroupType groupType = chooseGroup();

            if (groupType == null) manage();

            Group group = allGroups.findGroupByGroupType(groupType);

            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println(group);
            } else if (group == null){
                // 출력문으로 표시하지 않고 null 일 때 null 로 어떻게 출력하는가..
                System.out.println("GroupType: " + groupType);
                System.out.println("Parameter: " + null);
            }
        }
    }

    public int setMinimumTime() {
        while ( true ) {
            try {
                System.out.println("Input Minimum Spent Time: ");
                String minTime = nextLine(Message.END_MSG);
                return Integer.parseInt(minTime);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public int setMinimumPay() {
        while ( true ) {
            try {
                System.out.println("Input Minimum Total Pay : ");
                String minPay = nextLine(Message.END_MSG);
                return Integer.parseInt(minPay);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public void updateParameter() {

        while ( true ) {
            GroupType groupType = chooseGroup();
            // GroupType 에 해당하는 group 객체를 찾아야 함
            Group group = allGroups.findGroupByGroupType(groupType);

            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group);
                inputTimeAndPay(groupType);
            } else {
                // 파라미터가 없는경우
                System.out.println(Message.ERR_MSG_INVALID_PARAMETER_ARR_EMPTY);
                manage();
            }
        }
    }
}
