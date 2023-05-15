package me.smartstore.menu.customer;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Groups;
import me.smartstore.menu.Menu;
import me.smartstore.util.Message;

import java.awt.*;

public class AddCustomer implements Menu {
    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();

    //singleton
    private static AddCustomer addCustomer;

    public static AddCustomer getInstance(){
        if (addCustomer == null){
            addCustomer = new AddCustomer();
        }
        return addCustomer;
    }

    private AddCustomer(){}

    //

    public void addCustomer(){
        while(true){
            try {
                System.out.println("How many customer to input?");
                int n = Integer.parseInt(nextLineNotUpperCase(Message.END_MSG));

                for (int i = 0; i < n; i++) {
                    System.out.println("====== Customer "+  (i+1) + " Info. ======");
                    allCustomers.add(inputCustomerInfo(null));
                    // 고객정보 입력받는 함수 추가됨
                }
                allCustomers.refresh();
                break;
            }catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    
    public Customer inputCustomerInfo(Customer customer){
        //cusotmer이 null일 때 -> 새로운 고객객체를 생성할 때
        //cusotmer이 null이 아닐때 -> 기존 고객정보를 수정할 때
        String name = "";
        String id = "";
        Integer spentTime = 0;
        Integer totalPay = 0;
        if (customer != null){ // updateCustomer일 때 기존정보를 불러옴
            name = customer.getCustomerName();
            id = customer.getCustomerId();
            spentTime = customer.getCustomerTotalTime();
            totalPay = customer.getCustomerTotalPay();
        }

        while (true){
            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"});

            if (choice == 1) {
                name = inputCustomerName(name);
            } else if (choice == 2) {
                id = inputCustomerID(id);
            } else if (choice == 3) {
                spentTime = inputSpentTime(spentTime);
            } else if (choice == 4) {
                totalPay = inputTotalPay(totalPay);
            } else {
                return new Customer(name, id, spentTime, totalPay);
            }

        }

    }

    public String inputCustomerName(String originName){
        try {
            System.out.println("\nInput Customer's Name: ");
            String name = nextLineNotUpperCase(Message.END_MSG);

            return name;
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
            return originName;
        }

    }

    public String inputCustomerID(String originID){
        try{
            System.out.println("\nInput Customer's ID: ");
            String id = nextLineNotUpperCase(Message.END_MSG);
            return id;
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
            return originID;
        }
    }

    public Integer inputSpentTime(Integer originSpentTime){
        try{
            System.out.println("\nInput Customer's Spent Time: ");
            Integer spentTime = Integer.parseInt(nextLineNotUpperCase(Message.END_MSG));
            return spentTime;
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
            return originSpentTime;
        } catch (NumberFormatException e){
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            return originSpentTime;
        }
    }

    public Integer inputTotalPay(Integer originTotalPay){
        try{
            System.out.println("\nInput Customer's Total Payment: ");
            Integer totalPay = Integer.parseInt(nextLineNotUpperCase(Message.END_MSG));
            return totalPay;
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
            return originTotalPay;
        } catch (NumberFormatException e){
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            return originTotalPay;
        }
    }


    @Override
    public void manage() {

    }
}
