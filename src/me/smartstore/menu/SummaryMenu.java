package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.exception.constant.Message;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;

import java.util.Comparator;

public class SummaryMenu implements Menu{
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
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
    public void manage(){
        while (true) {
            try {
                int choice = chooseMenu(new String[]{
                        "Summary",
                        "Summary (Sorted By Name)",
                        "Summary (Sorted By Spent Time)",
                        "Summary (Sorted By Total Payment)",
                        "Back"
                });

                if (choice == 1) {
                    displaySummary();
                } else if (choice == 2) {
                    manageSort(Comparator.comparing(Customer::getCustomerName).thenComparing(Customer::getCustomerId));
                } else if (choice == 3) {
                    manageSort(Comparator.comparing(Customer::getCustomerTotalTime).thenComparing(Customer::getCustomerName));
                } else if (choice == 4) {
                    manageSort(Comparator.comparing(Customer::getCustomerTotalPay).thenComparing(Customer::getCustomerName));
                } else {
                    break;
                }

            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT.getMessage());
            }
        }
    }

    public void displaySummary() {
        try {
            Group group;
            Parameter parameter;

            for (int i = 0; i < allGroups.size(); i++) {
                group = allGroups.get(i);
                parameter = allGroups.get(i).getParameter();

                System.out.println("==============================");
                System.out.printf("Group : %s ( Time : %d, Pay : %d )", group.getGroupType(),
                        (parameter != null) ? parameter.getMinTime() : null,
                        (parameter != null) ? parameter.getMinPay() : null);
                System.out.println("\n==============================");
                System.out.print(allCustomers.findByGroup(group));
                System.out.println("==============================");
                System.out.println();
            }
        } catch (InputEndException e) {
            System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
        }
    }

    public void manageSort(Comparator<Customer> comparator) {
        while (true) {
            try {
                System.out.print("Which order (ASCENDING (A), DESCENDING (D))? ");
                String choice = nextLine("END");

                if (choice == null || choice.equals("END"))
                    return;

                SortType sortType = SortType.valueOf(choice).replaceFullName();

                Group group;
                Parameter parameter;

                for (int i = 0; i < allGroups.size(); i++) {
                    group = allGroups.get(i);
                    parameter = allGroups.get(i).getParameter();

                    System.out.println("==============================");
                    System.out.printf("Group : %s ( Time : %d, Pay : %d )", group.getGroupType(),
                            (parameter != null) ? parameter.getMinTime() : null,
                            (parameter != null) ? parameter.getMinPay() : null);
                    System.out.println("\n==============================");

                    if (sortType == SortType.ASCENDING) {
                        System.out.print(allCustomers.sort(allCustomers.findByGroup(group), comparator, String.valueOf(sortType)));
                    } else if (sortType == SortType.DESCENDING) {
                        System.out.print(allCustomers.sort(allCustomers.findByGroup(group), comparator, String.valueOf(sortType)));
                    } else {
                        throw new InputRangeException();
                    }
                    System.out.println("==============================");
                    System.out.println();
                }
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
                break;
            } catch (IllegalArgumentException | InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            }
        }
    }
}
