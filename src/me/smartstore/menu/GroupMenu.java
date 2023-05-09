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

    public void inputTimeAndPay(GroupType groupType) {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"});

            if (choice == 1) setMinimumTime(groupType);
            else if (choice == 2) setMinimumPay(groupType);
            else manage(); // choice == 3
        }

    }

    public GroupType chooseGroup() {
        while ( true ) {
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                // group (str) -> GroupType (enum)
                // "VIP" -> GroupType.VIP

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

            // GroupType에 해당하는 group 객체를 찾아야 함
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                Parameter parameter = new Parameter();
                // time, pay 사용자 입력받은 후, 설정 필요
                inputTimeAndPay(groupType);

                allCustomers.refresh(allGroups); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
            }
        }
    }

    public void viewParameter() {
        String choice = nextLine(Message.END_MSG);
        System.out.println("viewParameter 생성중");
        manage();
    }

    public void updateParameter() {
        System.out.println("updateParameter 생성중");
        manage();
    }

    public void setMinimumTime(GroupType groupType) {
        //group.setParameter(parameter);
        System.out.println("setMinimumTime 생성중");
        inputTimeAndPay(groupType);
    }

    public void setMinimumPay(GroupType groupType) {
        //group.setParameter(parameter);
        System.out.println("setMinimumPay 생성중");
        inputTimeAndPay(groupType);
    }
}
