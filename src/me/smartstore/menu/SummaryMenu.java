package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputTypeException;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.sort.SortedType;
import me.smartstore.util.Message;


public class SummaryMenu implements Menu {

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    private final int ASC = 1;
    private final int DESC = -1;
    private final int END = 0;

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
        while ( true ) {
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});
            if (choice == 1) summary(allCustomers.getCustomerArray());
            else if (choice == 2) sorted(SortedType.SORTED_NAME);
            else if (choice == 3) sorted(SortedType.SORTED_TIME);
            else if (choice == 4) sorted(SortedType.SORTED_PAY);
            else break;
        }
    }

    public int sortedType() {
        while(true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String choice = nextLine(Message.END_MSG);
                if (choice.equals("A")) {
                    return ASC;
                } else if (choice.equals("D")) {
                    return DESC;
                } else throw new InputTypeException();
            } catch (InputTypeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return END;
            }
        }
    }

    public void sorted(SortedType sortedType) {
        while(true) {
            int type = sortedType();
            if (type != END) {
                summary(allCustomers.sort(sortedType, type));
            } else break;
        }
    }

    public void summary(Customer[] customers) {
        GroupType[] groupType = {GroupType.NONE, GroupType.GENERAL, GroupType.VIP, GroupType.VVIP};
        for(int i=0; i<groupType.length; i++) {
            summaryView(customers, groupType[i]);
        }
    }

    public void summaryView(Customer[] customers, GroupType groupType) {
        Parameter parameter = null;
        for(int i=0; i<allGroups.size(); i++) {
            if(allGroups.get(i).getGroupType() == groupType) {
                parameter = allGroups.get(i).getParameter();
            }
        }
        System.out.println();
        System.out.println("===============================");
        if(parameter == null)
            System.out.printf("Group : %s ( Time : null, Pay : null )\n", groupType);
        else
            System.out.printf("Group : %s ( Time : %d, Pay : %d )\n", groupType, parameter.getMinTime(), parameter.getMinPay());
        System.out.println("===============================");
        int count = 0;
        for(int i=0; i<customers.length; i++) {
            if(customers[i].getGroup() == null) {
                if(groupType == GroupType.NONE)
                    System.out.printf("No.     %d => " + customers[i] + "\n", ++count);
                continue;
            }
            if(customers[i].getGroup().getGroupType() == groupType)
                System.out.printf("No.     %d => " + customers[i] + "\n", ++count);
        }
        if(count == 0)
            System.out.println("Null");
    }

}
