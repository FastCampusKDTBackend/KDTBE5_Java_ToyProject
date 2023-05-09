package menu;

import customer.Customer;
import customer.Customers;
import exception.InputEndException;
import exception.InputRangeException;
import group.Group;
import group.GroupType;
import group.Groups;
import group.Parameter;
import util.Message;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static group.GroupType.*;

public class SummaryMenu implements Menu{
    private static SummaryMenu summaryMenu;
    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();

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
            else if(choice == 2) summarySortByName(getCustomer());
            else if(choice == 3) summarySortBySpentTime(getCustomer());
            else if(choice == 4) summarySortByTotalPay(getCustomer());
            else break;
        }
    }
    /**
     * groupType별로 출력
     * */
    public void summary(Customer[] customers){
        Parameter parameter = null;
        Group group = null;
        GroupType[] groupType = {NONE, GENERAL, VIP, VVIP};
        for(GroupType type: groupType){
            for(int i=0; i<allGroups.size(); i++){
                if(allGroups.get(i).getGroupType() == type){
                    group = allGroups.get(i);
                    parameter = group.getParameter();
                }
            }
            System.out.println("==============================");
            if(parameter != null){
                System.out.println(type + ": " + type.name() + "( Time : " + parameter.getMinTime() + ", Pay : "+ parameter.getMinPay() + " )");
            }
            else{
                System.out.println(type + ": " + type.name() + "( Time : null  Pay : null )");
            }
            System.out.println("==============================");

            if(group != null) {
                System.out.println("group is not null");
                for (int i = 0; i < customers.length; i++) {
                    if(customers[i].getGroup().getGroupType() == type){
                        System.out.println(String.format("No. %d => ", i + 1) + customers[i]);
                    }
                }
            }
            else{
                System.out.println("Null.");
            }
            System.out.println("==============================");
            System.out.println("");
        }
    }

    /**
     * 이름순으로 정렬 내림차순 올림차순은 사용자가 설정
     * */
    public void summarySortByName(Customer[] customers){
        while(true){
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String choice = nextLine(Message.END_MSG);
                int flag = 0;
                if(!choice.equals("A") && !choice.equals("D")){
                    throw new InputRangeException();
                }
                if(choice.equals("A")){
                    flag = 1; //오름차순 정렬
                }
                else {
                    flag = -1; //내림차순 정렬
                }
                summary(sortCustomerByName(flag, customers)); //정렬된것 바탕으로 출력
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputRangeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
    //오름차순 혹은 내림차순으로 정렬
    public Customer[] sortCustomerByName(int flag, Customer[] customers){
        if(flag == 1){
            Arrays.sort(customers);
        }
        else{
            Arrays.sort(customers, Collections.reverseOrder());
        }
        return customers;
    }

    //customer을 배열로 반환
    public Customer[] getCustomer(){
        Customer[] customers = new Customer[allCustomers.size()];
        for (int i=0; i< allCustomers.size(); i++){
            customers[i] = allCustomers.get(i);
        }
        return customers;
    }
    /**
     * 시간으로 정렬
     * */
    public void summarySortBySpentTime(Customer[] customers){
        while(true){
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String choice = nextLine(Message.END_MSG);
                int flag = 0;
                if(!choice.equals("A") && !choice.equals("D")){
                    throw new InputRangeException();
                }
                if(choice.equals("A")){
                    flag = 1;
                }
                else {
                    flag = -1;
                }
                summary(sortCustomerByTime(flag, customers));
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputRangeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    //시간으로 오름차순 혹은 내림차순으로 정렬
    public Customer[] sortCustomerByTime(int flag, Customer[] customers){
        if(flag == 1){
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getTotalTime() - o2.getTotalTime();
                }
            });
        }
        else{
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o2.getTotalTime() - o1.getTotalTime();
                }
            });
        }
        return customers;
    }

    /**
     * 페이순으로 정렬
     * */
    public void summarySortByTotalPay(Customer[] customers){
        while(true){
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String choice = nextLine(Message.END_MSG);
                int flag = 0;
                if(!choice.equals("A") && !choice.equals("D")){
                    throw new InputRangeException();
                }
                if(choice.equals("A")){
                    flag = 1;
                }
                else {
                    flag = -1;
                }
                summary(sortCustomerByPay(flag, customers));
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputRangeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
    //페이를 오름차순 혹은 내림차순으로 정렬
    public Customer[] sortCustomerByPay(int flag, Customer[] customers){
        if(flag == 1){
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getTotalPay() - o2.getTotalPay();
                }
            });
        }
        else{
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o2.getTotalPay() - o1.getTotalPay();
                }
            });
        }
        return customers;
    }




}
