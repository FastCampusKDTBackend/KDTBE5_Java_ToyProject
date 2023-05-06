package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

public class CustomerMenu implements Menu {
    // singleton

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
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
            if(choice == 1) addCustomer();
            else if(choice == 2) viewCustomer();
            else if(choice == 3) updateCustomer();
            else if(choice == 4) deleteCustomer();
            else break;
        }
    }

    private void addCustomer() {
        while(true) {
            try {
                System.out.println("How many customers to input : ");
                int inputCount = Integer.parseInt(nextLine(Message.END_MSG));
                for(int i = 0; i < inputCount; i++) {
                    customerInfoInput(i + 1);
                }
                break;
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            }

        }
    }

    private void customerInfoInput(int count) {
        Customer addCustomer = new Customer();
        while(true) {
            System.out.printf("====== Customer %d Info. ======\n", count);
            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"
            });
            if(choice == 1) {
                System.out.print("Input Customer Name : ");
                String customerName = nextLine();
                addCustomer.setCusName(customerName);
            }else if (choice == 2) {
                System.out.print("Input Customer ID : ");
                String customerId = nextLine();
                addCustomer.setCusId(customerId);
            }else if(choice == 3) {
                System.out.print("Input Customer Spent Time : ");
                int customerSpentTime = Integer.parseInt(nextLine());
                addCustomer.setCusTotalTime(customerSpentTime);
            }else if(choice == 4) {
                System.out.print("Input Customer Total Pay : ");
                int customerTotalPay = Integer.parseInt(nextLine());
                addCustomer.setCusTotalPay(customerTotalPay);
            }else {
                allCustomers.add(addCustomer);
                allCustomers.refresh(allGroups);
                System.out.println(addCustomer);
                break;
            }
        }
    }

    private void viewCustomer() {
        System.out.println("======= Customer Info. =======");
        for(int i = 0; i < allCustomers.size(); i++) {
            System.out.printf("No. %3d => ", i + 1 );
            System.out.println(allCustomers.get(i));
        }
    }

    private void updateCustomer() {
        while(true) {
            try {
                viewCustomer();
                System.out.printf("Which customer ( 1 ~ %d ) ? : ", allCustomers.size());
                int updateIndex = Integer.parseInt(nextLine());
                Customer updateCustomer = allCustomers.get(updateIndex - 1);
                while(true) {
                    int choice = chooseMenu(new String[]{
                            "Customer Name",
                            "Customer ID",
                            "Customer Spent Time",
                            "Customer Total Pay",
                            "Back"
                    });
                    if(choice == 1) {
                        System.out.print("Input Customer Name : ");
                        String customerName = nextLine();
                        updateCustomer.setCusName(customerName);
                    }else if (choice == 2) {
                        System.out.print("Input Customer ID : ");
                        String customerId = nextLine();
                        updateCustomer.setCusId(customerId);
                    }else if(choice == 3) {
                        System.out.print("Input Customer Spent Time : ");
                        int customerSpentTime = Integer.parseInt(nextLine());
                        updateCustomer.setCusTotalTime(customerSpentTime);
                    }else if(choice == 4) {
                        System.out.print("Input Customer Total Pay : ");
                        int customerTotalPay = Integer.parseInt(nextLine());
                        updateCustomer.setCusTotalPay(customerTotalPay);
                    }else {
                        System.out.println(updateCustomer);
                        allCustomers.refresh(allGroups);
                        break;
                    }
                }
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }

        }
    }

    private void deleteCustomer() {
        while(true) {
            viewCustomer();
            System.out.printf("Which customer ( 1 ~ %d ) ? : ", allCustomers.size());
            int updateIndex = Integer.parseInt(nextLine());
            allCustomers.pop(updateIndex);
        }
        
    }
}
