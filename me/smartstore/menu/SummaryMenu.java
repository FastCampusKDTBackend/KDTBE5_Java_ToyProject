package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;

import java.util.Arrays;
import java.util.Collections;

import static me.smartstore.group.GroupType.*;


public class SummaryMenu implements Menu {
    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();
    private static SummaryMenu summaryMenu;

    public static SummaryMenu getInstance(){
        if(summaryMenu == null){
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }
    private SummaryMenu(){}

    @Override
    public void manage() {
        while (true) { //서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Spent Time)",
                    "Summary (Sorted By Total Payment)",
                    "Back"
            });
            if(choice == 1) summary(getCustomer());
            else if(choice == 2) sortedByName(getCustomer());
            else if(choice == 3) sortedBySpentTime(getCustomer());
            else if(choice == 4) sortedByTotalPayment(getCustomer());
            else break;
        }

    }
    public Customer[] getCustomer(){
        Customer[] customers = new Customer[allCustomers.size()];
        for (int i=0; i< allCustomers.size(); i++){
            customers[i] = allCustomers.get(i);
        }
        return customers;
    }
    public void summary(Customer[] customers){
        Parameter parameter = null;
        Group group;
        GroupType[] groupType = {NONE, GENERAL, VIP, VVIP};
        for(GroupType type: groupType){
            for(int i=0; i<allGroups.size(); i++){
                if(allGroups.get(i).getGroupType() == type){
                   group = allGroups.get(i);
                   parameter = group.getParameter();
                }
            }
            System.out.println("=====================================");
            System.out.println("Group : " + type.name() + "( Time : " + parameter.getMinTime() + ", Pay : "+ parameter.getMinPay() + " )");
            System.out.println("=====================================");

            for (int i = 0; i < customers.length; i++) {
                if(customers[i].getGroup().getGroupType() == type){
                    System.out.println(String.format("No. %d => ", i + 1) + customers[i]);
                }
            }
            System.out.println("");
        }
    }

    public OrderType orderTypeSummary() {
        while(true){
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String choice = nextLine(Message.END_MSG);
                OrderType orderType = OrderType.valueOf(choice).replaceFullName();
                return orderType;
            }catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }

    }


    public void sortedByName(Customer[] customers){

        while(true){
            OrderType orderType = orderTypeSummary();
            if(orderType == null){
                break;
            }
            try {
                if(orderType == OrderType.ASCENDING){
                  Arrays.sort(customers);
                }
                else if (orderType == OrderType.DESCENDING){
                    Arrays.sort(customers, Collections.reverseOrder());
                } else {
                    throw new InputRangeException();
                }
                summary(customers);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputRangeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }



    public void sortedBySpentTime(Customer[] customers){

    }


    public void sortedByTotalPayment(Customer[] customers){

    }


}