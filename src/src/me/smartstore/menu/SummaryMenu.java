package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;

import java.util.Arrays;
import java.util.InputMismatchException;

import static me.smartstore.util.Message.END_MSG;
import static me.smartstore.util.Message.ERR_MSG_INVALID_INPUT_RANGE;

public class SummaryMenu implements Menu {
    private static SummaryMenu summaryMenu;
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            return summaryMenu = new SummaryMenu();
        } else {
            return summaryMenu;
        }
    }

    private SummaryMenu() {
    }

    ;

    @Override
    public void manage() {
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});

            if (choice == 1) {
                showSummary(allCustomers.getCustomers());
            } else if (choice == 2) {
                sortedByName(allCustomers.getCustomers());
            } else if (choice == 3) {
                sortedByTime(allCustomers.getCustomers());
            } else if (choice == 4) {
               sortedByPay(allCustomers.getCustomers());
            } else {
                break;
            }
        }
    }

    public void showSummary(Customer[] customers) {
        for (int i = 0; i < allGroups.size(); i++) {
            Group group = allGroups.get(i);
            System.out.print("Group : " + group.getGroupType() + " ");
            System.out.print("( Time : " + group.getParameter().getMinTime() + ", ");
            System.out.println("Pay : " + group.getParameter().getMinPay() + ")");

            for (int j = 0; j < customers.length; j++) {
                Customer customer = customers[j];
                if (customer.getGroup().getGroupType() == group.getGroupType()) {
                    System.out.println("No. " + (j + 1) + " =>" + customer);
                }
            }
        }
    }

    private void sortedByName(Customer[] customers){
        while( true ) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String select = nextLine(END_MSG);

                if(!select.equals("A") && !select.equals("D")){
                    throw new InputMismatchException();
                }

                if (select.equals("A")){
                    Arrays.sort(customers, (o1, o2) -> o1.getCusName().compareTo(o2.getCusName()));
                    showSummary(customers);
                    break;
                }

                if (select.equals("D")) {
                    Arrays.sort(customers, (o1, o2) -> o2.getCusName().compareTo(o1.getCusName()));
                    showSummary(customers);
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
            }
            catch (InputEndException e){
                break;
            }
        }
    }

    private void sortedByTime(Customer[] customers){
        while( true ) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String select = nextLine(END_MSG);

                if(!select.equals("A") && !select.equals("D")){
                    throw new InputMismatchException();
                }

                if (select.equals("A")){
                    Arrays.sort(customers, (o1, o2) -> o1.getCusTotalTime().compareTo(o2.getCusTotalTime()));
                    showSummary(customers);
                    break;
                }

                if (select.equals("D")) {
                    Arrays.sort(customers, (o1, o2) -> o2.getCusTotalTime().compareTo(o1.getCusTotalTime()));
                    showSummary(customers);
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
            }
            catch (InputEndException e){
                break;
            }
        }
    }

    private void sortedByPay(Customer[] customers){
        while( true ) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String select = nextLine(END_MSG);

                if(!select.equals("A") && !select.equals("D")){
                    throw new InputMismatchException();
                }

                if (select.equals("A")){
                    Arrays.sort(customers, (o1, o2) -> o1.getCusTotalPay().compareTo(o2.getCusTotalPay()));
                    showSummary(customers);
                    break;
                }

                if (select.equals("D")) {
                    Arrays.sort(customers, (o1, o2) -> o2.getCusTotalPay().compareTo(o1.getCusTotalPay()));
                    showSummary(customers);
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
            }
            catch (InputEndException e){
                break;
            }
        }
    }
}
