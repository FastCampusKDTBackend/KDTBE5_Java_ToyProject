package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.utils.Message;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SummaryMenu implements Menu {

    private static SummaryMenu summaryMenu;

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    public static SummaryMenu getInstance(){
        if(summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Spent Time)",
                    "Summary (Sorted By Total Payment)",
                    "Back"});

            if (choice == 1) showSummary();
            else if (choice == 2) showSummarySort("Name");
            else if (choice == 3) showSummarySort("SpentTime");
            else if (choice == 4) showSummarySort("TotalPayment");
            else MainMenu.getInstance().manage();
        }
    }

    public GroupType[] distinctGroupTypeList() {
        GroupType[] groupType = GroupType.values();

        for (int i = 0; i < GroupType.values().length; i++) {
            groupType[i] = groupType[i].replaceFullName();
        }

        GroupType[] temp = new GroupType[GroupType.values().length];
        int cnt = 0;

        for (int i = 0; i < groupType.length; i++) {
            boolean flag = false;
            for (int j = i+1; j < groupType.length; j++){
                if (groupType[i].equals(groupType[j])) {
                    flag= true;
                }
            }
            if (!flag) {
                temp[cnt++] = groupType[i];
            }
        }
        GroupType[] result = new GroupType[cnt];

        for (int i = 0; i < cnt; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    private void showSummary() {
        GroupType[] groupType = distinctGroupTypeList();
        System.out.println(Arrays.toString(groupType));

        for (int i = 0; i < groupType.length; i++) {
            System.out.println("===============================");
            allGroups.viewGroupTimeAndPayByGroupType(groupType[i]);
            System.out.println("===============================");
            allCustomers.viewCustomerDataByGroupType(groupType[i]);
            System.out.println("===============================");
            System.out.println();
        }
    }

    private String getSummaryOrder() {
        while ( true ){
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String order = nextLine(Message.END_MSG);
                if (order.equals("A") || order.equals("D")) return order;
                else if (order.equals("end")) {
                    System.out.println(Message.ERR_MSG_INPUT_END);
                    return order;
                }
                throw new InputMismatchException();
            } catch (InputMismatchException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }
    }

    private void showSummarySort(String sortingMethod) {
        GroupType[] groupType = distinctGroupTypeList();
        System.out.println(Arrays.toString(groupType));

        while ( true ){
            try {
                String order = getSummaryOrder();

                for (int i = 0; i < groupType.length; i++) {
                    System.out.println("===============================");
                    allGroups.viewGroupTimeAndPayByGroupType(groupType[i]);
                    System.out.println("===============================");
                    allCustomers.viewCustomerDataByGroupType(groupType[i], sortingMethod, order);
                    System.out.println("===============================");
                    System.out.println();
                }
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                manage();
            }
        }
    }
}

