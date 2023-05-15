package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

import java.util.Scanner;

public class CustomerMenu implements Menu{

    //singleton
    private static CustomerMenu customerMenu;

    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();

    Scanner sc = new Scanner(System.in);

    public static CustomerMenu getInstance(){
        if(customerMenu == null)
            customerMenu = new CustomerMenu();
        return customerMenu;
    }

    private CustomerMenu(){}

    @Override
    public void manage() {
        while( true ){
            int choice = chooseMenu(new String[]{
                    "Add Customer",
                    "View Customer",
                    "Update Customer",
                    "Delete Customer",
                    "Back"});

            if(choice == 1)
                addCustomer();
            else if(choice == 2)
               viewCustomer();
           else if(choice == 3)
               updateCustomer();
           else if(choice == 4)
                deleteCustomer();
           else break; // choice == 5
       }
    }


    public void addCustomer() {
        while(true){
            try{
                System.out.println("How many customers to input?");
                int n = Integer.parseInt(nextLine(Message.END_MSG));

                for(int i = 0; i < n; i++){
                    System.out.println("===============");
                    System.out.println("User" + (i + 1) + " info");
                    System.out.println("===============");
                    Customer customer = new Customer();
                    chooseCustomerInfo(customer);
                    allCustomers.add(customer);

                }

                allCustomers.refresh(allGroups);
                break;

            }catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }


    }

    public void viewCustomer(){
        System.out.println(allCustomers);
    }

    public void updateCustomer(){
        chooseCustomerInfo(allCustomers.get(pickCustomer()));

        allCustomers.refresh(allGroups);
    }

    private void deleteCustomer() {
       Customer customer = allCustomers.pop(pickCustomer());
    }

    public int pickCustomer() {
        for (int i = 0; i < allCustomers.size(); i++) {
            System.out.println("No. " + (i + 1) + " => " + allCustomers.get(i));
        }
        while(true){
            try {
                System.out.print("Which customer ( 1 ~ " + allCustomers.size() + " )? ");
                int n = Integer.parseInt(nextLine());

                if(n > allCustomers.size()){
                    System.out.println("Index Out of Bounds. Please try again.");
                    continue;
                }

                return n;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }

    }

    public void chooseCustomerInfo(Customer customer){
        while( true ){
            try{
                int choice = chooseMenu(new String[]{
                        "Customer Name",
                        "Customer ID",
                        "Customer Spent Time",
                        "Customer Total Pay",
                        "Back"});

                if(choice == 1){
                    System.out.println("Input Customer's name:");
                    String name = nextLine(Message.END_MSG);
                    customer.setCusName(name);
                }
                else if(choice == 2){
                    System.out.println("'Input Customer's ID:");
                    String id = nextLine(Message.END_MSG);
                    customer.setCusId(id);
                }
                else if(choice == 3){
                    System.out.println("'Input Customer's Spent Time:");
                    int spentTime = Integer.parseInt(nextLine(Message.END_MSG));
                    customer.setCusTotalTime(spentTime);
                }
                else if(choice == 4){
                    System.out.println("'Input Customer's Total Pay:");
                    int totalPay = Integer.parseInt(nextLine(Message.END_MSG));
                    customer.setCusTotalPay(totalPay);
                }
                else break; // choice == 5
            }catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
            }

        }

    }
}
