package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

public class SummaryMenu implements Menu {

    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();
    private static SummaryMenu summaryMenu;

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {
    }

    @Override
    public void manage() {
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});
            if (choice == 1) summary();
            else if (choice == 2) summaryByName();
            else break; // choice == 4
        }
    }

    private void show(Customer[] customers, Group group) {
        try {
            System.out.println("==============================");
            System.out.printf("Group : %s ( Time : %d, Pay : %d )\n", group.getGroupType(),
                    group.getParameter().getMinTime(),
                    group.getParameter().getMinPay());
            System.out.println("==============================");
            for (int i = 0; i < customers.length; i++) {
                System.out.println("NO.\t" + (i + 1) + " => " + customers[i]);
            }
            System.out.println("==============================");
            System.out.println();


        } catch (NullPointerException e) {
            System.out.println("그룹을 찾을 수 없다");
        }
    }

    private int sortType() {
        while (true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String choice = nextLine(Message.END_MSG);

                if (!choice.equals("A") && !choice.equals("D") && !choice.equals("a") && !choice.equals("d")) {
                    throw new InputRangeException();
                }

                if (choice.equals("a") || choice.equals("A")) return 1;
                else return -1;

            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }



        private void summary() {
           int mark = 0;
           for(int i = 0; i < allGroups.size(); i++) {
               Customer[] value = allCustomers.seperateGroup(allGroups.get(i).getGroupType());
               show(value, allGroups.get(i));
           }
        }

        private void summaryByName() {
            int sortcount = 0;
            sortcount = sortType();
            for(int i = 0; i < allGroups.size(); i++){
                Customer[] value = allCustomers.seperateGroup(allGroups.get(i).getGroupType());
                allCustomers.sortName(value, sortcount);
                show(value, allGroups.get(i));
            }
        }

    }



