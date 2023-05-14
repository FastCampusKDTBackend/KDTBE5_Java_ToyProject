package menu;

import arrays.DArray;
import customer.Customer;
import customer.Customers;
import exception.InputEndException;
import exception.InputRangeException;
import group.Group;
import group.GroupType;
import group.Groups;
import group.Parameter;
import util.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SummaryMenu implements Menu {

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    private static SummaryMenu summaryMenu;

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            try {
                int choice = chooseMenu(new String[]{
                        "Summary",
                        "Summary (Sorted By Name)",
                        "Summary (Sorted By Time)",
                        "Summary (Sorted By Pay)",
                        "Back"});
                if (choice == 1) {
                    summary();
                } else if (choice == 2) {
                    summaryByName();
                } else if (choice == 3) {
                    summaryByTime();
                } else if (choice == 4) {
                    summaryByPay();
                } else {
                    break; // choice == 5
                }
            } catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            }
        }
    }

    public void summary () {

        int cnt = allGroups.size();
        for (int i = 0; i < cnt; i++) {
            Group group = allGroups.get(i);
            GroupType grouptype = group.getGroupType();
            int minTime = group.getParameter().getMinTime();
            int minPay = group.getParameter().getMinPay();
            System.out.println("\n" + "==============================" + "\n" +
                                "Group : " + grouptype + " ( Time : " + minTime + ", Pay : " + minPay + " )" + "\n" +
                                "==============================");

            int idx = 1;
            for(int j=0; j<allCustomers.size(); j++){
                if (allCustomers.get(j).getGroup().equals(group)) {
                    System.out.print("No. " + idx++ + " => ");
                    System.out.println(allCustomers.get(j));
                }
            }
        }
    }

    public void summaryByName() {
        String choice = summaryAorD();
        //??
        int cnt = allGroups.size();
        for (int i = 0; i < cnt; i++) {
            Group group = allGroups.get(i);
            GroupType grouptype = group.getGroupType();
            int minTime = group.getParameter().getMinTime();
            int minPay = group.getParameter().getMinPay();
            System.out.println("\n" + "==============================" + "\n" +
                    "Group : " + grouptype + " ( Time : " + minTime + ", Pay : " + minPay + " )" + "\n" +
                    "==============================");

            int idx = 1;
            for(int j=0; j<allCustomers.size(); j++){
                if (allCustomers.get(j).getGroup().equals(group)) {
                    System.out.print("No. " + idx++ + " => ");
                    System.out.println(allCustomers.get(j));
                }
            }

        }
    }

    public String summaryAorD() {
        while (true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?" + "\n");
                String choice = nextLine(Message.END_MSG);

                if (choice.equals("A") || choice.equals("B"))
                    return choice;
                throw new IllegalArgumentException(); // choice 가 범위에 벗어
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            }
        }
    }

    public void summaryByTime() {
        String choice = summaryAorD();
        //??
        int cnt = allGroups.size();
        for (int i = 0; i < cnt; i++) {
            Group group = allGroups.get(i);
            GroupType grouptype = group.getGroupType();
            int minTime = group.getParameter().getMinTime();
            int minPay = group.getParameter().getMinPay();
            System.out.println("\n" + "==============================" + "\n" +
                    "Group : " + grouptype + " ( Time : " + minTime + ", Pay : " + minPay + " )" + "\n" +
                    "==============================");

            int idx = 1;
            for(int j=0; j<allCustomers.size(); j++){
                if (allCustomers.get(j).getGroup().equals(group)) {
                    System.out.print("No. " + idx++ + " => ");
                    System.out.println(allCustomers.get(j));
                }
            }
        }

    }

    public void summaryByPay() {
        String choice = summaryAorD();
        //??
        int cnt = allGroups.size();
        for (int i = 0; i < cnt; i++) {
            Group group = allGroups.get(i);
            GroupType grouptype = group.getGroupType();
            int minTime = group.getParameter().getMinTime();
            int minPay = group.getParameter().getMinPay();
            System.out.println("\n" + "==============================" + "\n" +
                    "Group : " + grouptype + " ( Time : " + minTime + ", Pay : " + minPay + " )" + "\n" +
                    "==============================");

            int idx = 1;
            for(int j=0; j<allCustomers.size(); j++){
                if (allCustomers.get(j).getGroup().equals(group)) {
                    System.out.print("No. " + idx++ + " => ");
                    System.out.println(allCustomers.get(j));
                }
            }
        }
    }

}
