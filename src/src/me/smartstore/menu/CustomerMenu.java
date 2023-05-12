package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

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
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Add Customer",
                    "View Customer",
                    "Update Customer",
                    "Delete Customer",
                    "Back"});
            if( choice == 1){
                addCustomer();
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

    private void addCustomer(){
        while( true ) {
            try {
                System.out.println("How many customers to input?");
                Integer number = formatInt(nextLine(END_MSG));

                for (int i = 1; i <= number; i++) {
                    System.out.printf("====== Customer %d Info. ======%n", i);
                    Customer customer = new Customer();
                    setCustomerInfo(customer);
                    allCustomers.add(customer);
                }
                allCustomers.refresh();
                break;
            }
            catch (InputEndException e){
                break;
            }
            catch (InputMismatchException e){
                System.out.println(ERR_MSG_INVALID_INPUT_FORMAT);
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
                    setCustomerName(customer);
                }
                else if (choice == 2) {
                    System.out.println("Input Customer's ID: ");
                    setCustomerId(customer);
                }
                else if (choice == 3) {
                    System.out.println("Input Customer's Spent Time:");
                    setCustomerTotalTime(customer);
                } 
                else if (choice == 4) {
                    System.out.println("Input Customer's Total Payment: ");
                    setCustomerTotalPay(customer);
                }
                else{
                    break;
                }
            }catch (InputEndException e){
                break;
            }
            catch (InputMismatchException e){
                System.out.println(ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }
    }

    private void viewCustomer(){
        System.out.println("======= Customer Info. =======");

        for (int i = 0; i < allCustomers.size() ; i++) {
            System.out.printf("No. %d => %s%n", (i+1), allCustomers.get(i).toString());
        }
    }

    private void updateCustomer() {
        viewCustomer();
        while (true) {
            System.out.printf("Which customer ( 1 ~ %d )%n", allCustomers.size());
            try {
                Integer index = formatInt(nextLine());
                if (index < 1 || index > allCustomers.size()) {
                    throw new InputRangeException();
                }
                Customer customer = allCustomers.get(index - 1);
                setCustomerInfo(customer);
                allCustomers.refresh();
                return;
            } catch (InputMismatchException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    private void deleteCustomer() {
        viewCustomer();
        while (true) {
            System.out.printf("Which customer ( 1 ~ %d )%n", allCustomers.size());
            try {
                Integer index = formatInt(nextLine(END_MSG));
                if (index < 1 || index > allCustomers.size()) {
                    throw new InputRangeException();
                }
                System.out.println(allCustomers.pop(index - 1));
                return;
            } catch (InputRangeException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
            } catch (InputMismatchException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }
    }

    private void setCustomerName(Customer customer) throws InputEndException{
        customer.setCusName(nextLine(END_MSG));
    }

    private void setCustomerId(Customer customer) throws InputEndException{
        customer.setCusId(nextLine(END_MSG));
    }

    private void setCustomerTotalTime(Customer customer) throws InputEndException{
        customer.setCusTotalTime(formatInt(nextLine(END_MSG)));
    }
    private void setCustomerTotalPay(Customer customer) throws InputEndException{
        customer.setCusTotalPay(formatInt(nextLine(END_MSG)));
    }
}
