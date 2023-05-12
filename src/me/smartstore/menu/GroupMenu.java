package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;


public class GroupMenu implements Menu{
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
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) setParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else break; // choice == 4
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
            if (groupType == null){ //chooseGroup에서 end입력되면 null리턴함
                allCustomers.refresh(allGroups); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
                break;
            }

            // GroupType에 해당하는 group 객체를 찾아야 함
            Group group = allGroups.find(groupType);

            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            }
            else{
                Parameter parameter = new Parameter();
                // time, pay 사용자 입력받은 후, 설정 필요
                while( true ){
                    int choice = chooseMenu(new String[]{
                            "Minimum Spent Time",
                            "Minimum Total Pay",
                            "Back"});
                    if (choice == 1) {
                        parameter.setMinTime(inputMinimumSpentTime());
                    }
                    else if (choice == 2) {
                        parameter.setMinPay(inputMinimumTotalPay());
                    }
                    else{
                        allGroups.add(new Group(parameter, groupType));
                        group = allGroups.find(groupType);
                        System.out.println("\nGroupType: " + group.getGroupType());
                        System.out.println("Parameter: " + group.getParameter());
                        allCustomers.refresh(allGroups);
                        break;
                    }
                }

            }
        }
    }

    public void viewParameter() {
        while ( true ){
            try {
                GroupType groupType = chooseGroup();
                if (groupType == null) break;

                Group group = allGroups.find(groupType);
                System.out.println("GroupType: " + group.getGroupType());
                System.out.println("Parameter: " + group.getParameter());
            } catch (NullPointerException e){
                System.out.println("There is no Group Parmeter.\nPlease set a Parameter.");
            }

        }
    }

    public void updateParameter(){
        while( true ) {
            GroupType groupType = chooseGroup();
            if (groupType == null){ //chooseGroup에서 end입력되면 null리턴함
                allCustomers.refresh(allGroups); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
                break;
            }

            Group group = allGroups.find(groupType);
            System.out.println("\nGroupType: " + group.getGroupType());
            System.out.println("Parameter: " + group.getParameter());

            while ( true ){
                int choice = chooseMenu(new String[]{
                        "Minimum Spent Time",
                        "Minimum Total Pay",
                        "Back"});

                if (choice == 1) {
                    Integer minimumSpentTime = inputMinimumSpentTime();
                    if (minimumSpentTime == null) continue;
                    else group.getParameter().setMinTime(minimumSpentTime);
                }
                else if (choice == 2) {
                    Integer minimumTotalPay = inputMinimumTotalPay();
                    if (minimumTotalPay == null) continue;
                    else group.getParameter().setMinPay(minimumTotalPay);
                }
                else{
                    System.out.println("\nGroupType: " + group.getGroupType());
                    System.out.println("Parameter: " + group.getParameter());
                    allCustomers.refresh(allGroups);
                    break;
                }
            }


        }
    }

    public Integer inputMinimumSpentTime(){
        while (true){
            try{
                System.out.println("\nInput Minimum Spent Time: ");
                Integer minimumSpentTime = Integer.parseInt(nextLine(Message.END_MSG));
                if(minimumSpentTime<0) throw new InputRangeException();
                return minimumSpentTime;
            } catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (InputRangeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            }
        }
    }

    public Integer inputMinimumTotalPay(){
        while (true){
            try {
                System.out.println("\nInput Minimum Total Pay: ");
                Integer minimumTotalPay = Integer.parseInt(nextLine(Message.END_MSG));
                if(minimumTotalPay<0) throw new InputRangeException();
                return minimumTotalPay;
            } catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (InputRangeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            }
        }
    }
}
