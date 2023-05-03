package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.util.Message;

import java.util.InputMismatchException;

import static me.smartstore.util.Message.*;

public class CustomerMenu implements Menu{
    private static CustomerMenu customerMenu;
    private final Customers allCustomers = Customers.getInstance();

    public static CustomerMenu getInstance(){
        if(customerMenu == null){
            return customerMenu = new CustomerMenu();
        }
        else {
            return customerMenu;
        }
    }

    private CustomerMenu(){};

    public void manage() {
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Add Customer",
                    "View Customer",
                    "Update Customer",
                    "Delete Customer",
                    "Back"});
            if( choice == 1){
                createCustomer();
            }
            else if(choice == 2){
                viewCustomer();
            }else if(choice == 3){
                updateCustomer();
            }
            else if(choice == 4){
               deleteCustomer();
            }
            else {
                break;
            }
        }
    }

    private void createCustomer(){
        while( true ) {
            try {
                System.out.println("How many customers to input?");
                String inputData = nextLine(END_MSG);
                Integer number = formatInt(inputData);

                for (int i = 1; i <= number; i++) {
                    System.out.println("====== Customer " + i + " Info. ======");
                    Customer customer = new Customer();
                    setCustomerInfo(customer);
                    allCustomers.add(customer);
                }
                return;
//                allCustomers.refresh(); 구현 해야 함
            }
            catch (InputEndException e){
                break;
            }
        }
    }

    private void setCustomerInfo(Customer customer){
        while( true ){
            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"
            });
            try{
                if(choice == 1){
                    System.out.println("Input Customer's Name:");
                    customer.setCusName(nextLine(END_MSG));
                }
                else if (choice == 2) {
                    System.out.println("Input Customer's ID: ");
                    customer.setCusId(nextLine(END_MSG));
                }
                else if (choice == 3) {
                    System.out.println("Input Customer's Spent Time:");
                    customer.setCusTotalTime(formatInt(nextLine(END_MSG)));
                } 
                else if (choice == 4) {
                    System.out.println("Input Customer's Total Payment: ");
                    customer.setCusTotalPay(formatInt(nextLine(END_MSG)));
                }
                else{
                    break;
                }
            }catch (InputEndException e){
                break;
            }
        }
    }

    private void viewCustomer(){
        System.out.println("======= Customer Info. =======");

        for (int i = 0; i < allCustomers.size() ; i++) {
            System.out.println("No. " + (i+1) +" =>" + allCustomers.get(i));
        }
    }

    private void updateCustomer(){
        viewCustomer();
        System.out.println("Which customer " + "( 1 ~ " + allCustomers.size() +")");
        try{
            Integer index = formatInt(nextLine());
            if (index < 1 || index > allCustomers.size()) {
                throw new InputRangeException();
            }
            Customer customer = allCustomers.get(index-1);
            setCustomerInfo(customer);
//            allCustomers.refresh();
            return;
        }
        catch (InputMismatchException e){
            System.out.println(ERR_MSG_INVALID_INPUT_FORMAT);
        }
        catch (InputRangeException e ){
            System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
        }
    }

    private void deleteCustomer(){
        viewCustomer();
        System.out.println("Which customer " + "( 1 ~ " + allCustomers.size() +")");
        try{
            Integer index = formatInt(nextLine(END_MSG));
            if (index < 1 || index > allCustomers.size()) {
                throw new InputRangeException();
            }
            Customer customer = allCustomers.get(index - 1);
            System.out.println(allCustomers.pop(index-1));
//            allCustomers.refresh();
            return;
        }
        catch (InputEndException e){
            System.out.println(ERR_MSG_INVALID_INPUT_FORMAT);
        }
    }
}
