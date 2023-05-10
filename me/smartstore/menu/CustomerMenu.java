package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.EmptyArrayException;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.util.Message;

import java.util.InputMismatchException;

public class CustomerMenu implements Menu {
    // singleton
    private final Customers allCustomers = Customers.getInstance();
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {
    }

    @Override
    public void manage() {
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Add Customer",
                    "View Customer",
                    "Update Customer",
                    "Delete Customer",
                    "Back"});

            if (choice == 1) addCustomer();
            else if (choice == 2)
                viewCustomer();
            else if (choice == 3)
                updateCustomer();
             else if (choice == 4)
                deleteCustomer();
             else break; // choice == 4
        }
    }


    private Customer setCustomer(Customer customer) {
        while (true) {
            try {
                int choice = customerMenu.chooseMenu(new String[]{
                        "Customer Name",
                        "Customer ID",
                        "Customer Spent Time",
                        "Customer Total Pay",
                        "Back"
                });

                if (choice == 1) {
                    System.out.println("Input Customer's Name: ");
                    customer.setCusName(nextLine(Message.END_MSG));

                } else if (choice == 2) {
                    System.out.println("Input Customer's ID: ");
                    customer.setCusId(nextLine(Message.END_MSG));
                } else if (choice == 3) {
                    System.out.println("Input Customer's Spent Time: ");
                    customer.setCusTotalTime(Integer.valueOf(nextLine(Message.END_MSG)));
                } else if (choice == 4) {
                    System.out.println("Input Customer's Total Payment: ");
                    customer.setCusTotalPay(Integer.valueOf((nextLine(Message.END_MSG))));
                } else
                    break;

            } catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            }
        }
        return customer;
    }


    private void addCustomer() {
        while (true) {
            try {
                System.out.println("How many customer to input?");
                int customers = Integer.parseInt(nextLine(Message.END_MSG));

                for (int i = 0; i < customers; i++) {
                    System.out.println("\n====== Customer " + (i + 1) + " Info. ======");
                    Customer sCustomer = new Customer();
                    sCustomer = setCustomer(sCustomer);
                    allCustomers.add(sCustomer);
                    allCustomers.refresh();
                }
                return;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }

        }
    }


    private void  viewCustomer() {

        try {
            System.out.println("\n======= Customer Info. =======");
                for (int i = 0; i < allCustomers.size(); i++) {
                    System.out.print("NO. " + (i + 1) + "=> ");
                    System.out.println(allCustomers.get(i));
                    }
                    allCustomers.refresh();

                } catch (EmptyArrayException e) {
                    System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
                } catch (InputEndException e) {
                    System.out.println(Message.ERR_MSG_INPUT_END);
                }
    }

    private void updateCustomer () {
            viewCustomer();
            try{
                System.out.print("Which customer ( 1 ~ " + allCustomers.size() + " )? ");
                int cusNum = Integer.parseInt(nextLine(Message.END_MSG));
                setCustomer(allCustomers.get(cusNum -1) );
                allCustomers.refresh();

            }catch(InputMismatchException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }catch(InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
            }
    }

    private void deleteCustomer () {
        viewCustomer();
        try{
            System.out.print("Which customer ( 1 ~ " + allCustomers.size() + " )? ");
            int cusNum = Integer.parseInt(nextLine(Message.END_MSG));

            System.out.println(allCustomers.pop(cusNum - 1));
            allCustomers.refresh();
            viewCustomer();


        }catch(InputMismatchException e){
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
        }catch(InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
        }



    }
}
