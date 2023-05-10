package me.smartstore.menu.customer;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Groups;
import me.smartstore.menu.Menu;
import me.smartstore.util.Message;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerMenu implements Menu {
    private final Customers allCustomers = Customers.getInstance();
    private final AddCustomer addCustomer = AddCustomer.getInstance();
    private final Groups allGroups = Groups.getInstance();
    Scanner scanner = new Scanner(System.in);

    // singleton
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Add Customer",
                    "View Customer",
                    "Update Customer",
                    "Delete Customer",
                    "Back"});
            if (choice == 1) addCustomer.addCustomer();
            else if (choice == 2) viewCustomer();
            else if (choice == 3) updateCustomer();
            else if (choice == 4) deleteCustomer();
            else break;
        }
    }


    public void viewCustomer(){
        System.out.println("======= Customer Info. =======");
        for (int i = 0; i < allCustomers.size(); i++) {

            System.out.printf("No. %d => Customer{userId='%s', name='%s', spentTime=%d, totalPay=%d, "
                    , i+1,allCustomers.get(i).getCustomerId(),allCustomers.get(i).getCustomerName(),allCustomers.get(i).getCustomerTotalTime(),allCustomers.get(i).getCustomerTotalPay());
            if (allCustomers.get(i).getGroup() == null){
                System.out.println("group=GroupType: null");
            }
            else {
                System.out.println("group=GroupType: " + allCustomers.get(i).getGroup().getGroupType());
            }
        }
    }

    public void updateCustomer(){
        // 고객객체를 업데이트 할 때 기존 정보를 불려와야함.
        viewCustomer();
        while (true){
            System.out.printf("\nWhich customer (1 ~ %d) ?", allCustomers.size());
            try {
                int num = scanner.nextInt();
                scanner.nextLine();
                if (num > allCustomers.size()) throw new InputMismatchException();
                allCustomers.set(num-1, addCustomer.inputCustomerInfo(allCustomers.get(num-1)));
                allCustomers.refresh(allGroups);
                break;
            }catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                scanner.nextLine();
            }
        }


    }

    public void deleteCustomer(){
        viewCustomer();
        while (true){
            System.out.printf("\nWhich customer (1 ~ %d)? ", allCustomers.size());
            try {
                int num = scanner.nextInt();
                scanner.nextLine();
                if (num > allCustomers.size()) throw new InputMismatchException();
                allCustomers.pop(num-1);
                break;
            }catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                scanner.nextLine();
            }
        }



    }
}