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

    //-----

    public void addCustomer(){ //Todo : update에서도 활용가능하게 함수화 하기, 이 함수 클래스화 가능한지 생각해보기
        while(true){
            try {
                System.out.println("How many customer to input?");
                int n = Integer.parseInt(nextLine(Message.END_MSG));

                for (int i = 0; i < n; i++) {
                    System.out.println("====== Customer "+  (i+1) + " Info. ======");
                    allCustomers.add(inputCustomerInfo(null));
                    // 고객정보 입력받는 함수 추가됨
                }
                allCustomers.refresh(allGroups);
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
        if (customer != null){
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
                name = inputCustomerName();
                if(name == null) continue;
            } else if (choice == 2) {
                id = inputCustomerID();
                if(id == null) continue;
            } else if (choice == 3) {
                try{
                    spentTime = inputSpentTime();
                }catch (NullPointerException e){
                    continue;
                }

            } else if (choice == 4) {
                try{
                    totalPay = inputTotalPay();
                }catch (NullPointerException e){
                    continue;
                }
            } else {
                return new Customer(name, id, spentTime, totalPay);
            }

        }

    }

    public String inputCustomerName(){
        try {
            System.out.println("\nInput Customer's Name: ");
            String name = nextLineNotUpperCase(Message.END_MSG);

            return name;
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
            return null;
        }

    }

    public String inputCustomerID(){
        try{
            System.out.println("\nInput Customer's ID: ");
            String id = nextLineNotUpperCase(Message.END_MSG);
            return id;
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
            return null;
        }
    }

    public Integer inputSpentTime(){
        try{
            System.out.println("\nInput Customer's Spent Time: ");
            Integer spentTime = Integer.parseInt(nextLine(Message.END_MSG));
            return spentTime;
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
            return null;
        } catch (IllegalArgumentException e){
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            return null;
        }
    }

    public Integer inputTotalPay(){
        try{
            System.out.println("\nInput Customer's Total Payment: ");
            Integer totalPay = Integer.parseInt(nextLine(Message.END_MSG));
            return totalPay;
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
            return null;
        } catch (IllegalArgumentException e){
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            return null;
        }
    }


    @Override
    public void manage() {

    }
}
