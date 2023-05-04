package Menu;

import CustomException.InputEndException;
import CustomException.InputRangeException;
import CustomException.Message;
import Customer.Customer;
import Customer.Customers;
import Group.Group;
import Group.GroupType;
import Group.Groups;

import java.util.Arrays;
import java.util.Comparator;

public class SummaryMenu implements Menu {
    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();
    private static SummaryMenu allSummaryMenu;

    public static SummaryMenu getInstance() {
        if (allSummaryMenu == null) {
            allSummaryMenu = new SummaryMenu();
        }
        return allSummaryMenu;
    }

    private SummaryMenu() {

    }

    @Override
    public void manage() {
        while (true) {
            int choice = allSummaryMenu.chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Total Payment)",
                    "Back"
            });

            if (choice == 1) summary(1);
            else if (choice == 2) summary(2);
            else if (choice == 3) summary(3);
            else if (choice == 4) summary(4);
            else if (choice == 5) break;
        }
    }

    private void summary(int choice) {
        int mark = 0;
        if (choice == 2 || choice == 3 || choice == 4) {
            mark = setSortBy();
        }

        for (int i = 0; i < allGroups.size(); i++) {
            Customer[] value = allCustomers.separateOfRank(allGroups.get(i).getGroupType());

            if (choice == 2) allCustomers.sortByNameSummary(value, mark);
            else if (choice == 3) allCustomers.sortByTimeSummary(value, mark);
            else if (choice == 4) allCustomers.sortByPaySummary(value, mark);

            showChart(value, allGroups.get(i));
        }
    }

    private int setSortBy() {
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

    private void showChart(Customer[] customers, Group group) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==============================\n");
        sb.append("Group : " + group.getGroupType());
        sb.append(" ( Time : " + group.getParameter().getMinTime());
        sb.append(", Pay : " + group.getParameter().getMinPay() + " )");
        sb.append("\n==============================\n");
        for (int i = 0; i < customers.length; i++) {
            sb.append("No.\t" + (i + 1) + " => ");
            sb.append(customers[i] + "\n");
        }
        sb.append("==============================\n");
        System.out.println(sb);
    }

}
