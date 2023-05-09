package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.CustomerArrayEmptyException;
import me.smartstore.utils.Message;

public class CustomerMenu implements Menu {
    private static CustomerMenu customerMenu;
    private final Customers allCustomers = Customers.getInstance();

    public static CustomerMenu getInstance() {
        if(customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Add Customer Data",
                    "View Customer Data",
                    "Update Customer Data",
                    "Delete Customer Data",
                    "Back"});

            if (choice == 1) addCustomer();
            else if (choice == 2) viewCustomer();
            else if (choice == 3) updateCustomer();
            else if (choice == 4) deleteCustomer();
            else MainMenu.getInstance().manage();
        }
    }

    private void addCustomer() {
        System.out.println("How many customers to input?");
        int addCustomer = Integer.parseInt(nextLine(Message.END_MSG));
        int cnt = 1;
        while ( cnt <= addCustomer ){
            System.out.println("====== Customer " + cnt + " Info. ======");
            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"});

            // Customer 에 새로운 고객 추가
            if (choice == 1) addCustomerName();
            else if (choice == 2) addCustomerID();
            else if (choice == 3) addCustomerSpentTime();
            else if (choice == 4) addCustomerTotalPay();
            else cnt++;
        }
    }

    private void viewCustomer() {
        System.out.println("======= Customer Info. =======");
        allCustomers.viewCustomerData();
    }

    private void updateCustomer() {
        System.out.println("updateCustomer 생성중");
        allCustomers.viewCustomerData();
        while ( true ) {
            try{
                int choiceNum = nextLine(allCustomers.size());
//                return choiceNum;
                //고객 선택 후 변경 add 할 때의 Customer 내용 셀렉이 또 필요
            } catch (CustomerArrayEmptyException e){
                System.out.println(e.getMessage());
            }
        }
//        if( allCustomers.size() > 1) {        }
    }

    private void deleteCustomer() {
        System.out.println("deleteCustomer 생성중");
    }

    private void addCustomerName() {
        System.out.println("addCustomerName 생성중");
    }

    private void addCustomerID() {
        System.out.println("addCustomerID 생성중");
    }

    private void addCustomerSpentTime() {
        System.out.println("addCustomerSpentTime 생성중");
    }

    private void addCustomerTotalPay() {
        System.out.println("addCustomerTotalPay 생성중");
    }
}
