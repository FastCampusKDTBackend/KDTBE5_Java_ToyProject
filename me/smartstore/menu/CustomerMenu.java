package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

import me.smartstore.util.Message;


public class CustomerMenu implements Menu{
    private final Customers allCustomers = Customers.getInstance();
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance(){
        if(customerMenu == null){
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }
    private CustomerMenu(){}

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Set Customer Data",
                    "View Customer Data",
                    "Update Customer Data",
                    "Delete Customer Data",
                    "Back"
            });
            if(choice == 1) setCustomer();
            else if(choice == 2) viewCustomer();
            else if(choice == 3) updateCustomer();
            else if(choice == 4) deleteCustomer();
            else break;
        }
    }

  public void setCustomer(){
      while(true){
          System.out.println("How many customers to input?");
          try{
              String num = nextLine(Message.END_MSG);
              for(int i=0; i<Integer.parseInt(num); i++){
                  System.out.printf("======= Customer %d Info. ======= \n", i+1);
                  Customer customer = new Customer();
                  setCustomerManage(customer);
                  allCustomers.add(customer);
                  allCustomers.refresh();
              }
              break;
          } catch (InputEndException e){
              System.out.println(Message.ERR_MSG_INPUT_END);
              break;
          }
      }
  }

   private void setCustomerManage(Customer customer) {
       while (true) {
           int choice = chooseMenu(new String[]{
                   "Customer Name",
                   "Customer ID",
                   "Customer Spent Time",
                   "Customer Total Pay",
                   "Back"
           });
           if(choice == 1) setCustomerName(customer);
           else if(choice == 2) setCustomerID(customer);
           else if(choice == 3) setCustomerSpentTime(customer);
           else if(choice == 4) setCustomerTotalPay(customer);
           else break;
       }
   }

   private void setCustomerName(Customer customer) {
       System.out.println("Input Customer's Name : ");
       try{
           String name = nextLine(Message.END_MSG);
           customer.setcName(name);
       } catch (InputEndException e){
           System.out.println(Message.ERR_MSG_INPUT_END);
       }
   }

   private void setCustomerID(Customer customer) {
       System.out.println("Input Customer's ID : ");
       try{
           String id = nextLine(Message.END_MSG);
           customer.setcId(id);
       } catch (InputEndException e){
           System.out.println(Message.ERR_MSG_INPUT_END);
       }
   }

   private void setCustomerSpentTime(Customer customer) {
       System.out.println("Input Customer's Spent Time : ");
       try{
           String time = nextLine(Message.END_MSG);
           customer.setTotalTime(Integer.parseInt(time));
       } catch (InputEndException e){
           System.out.println(Message.ERR_MSG_INPUT_END);
       }
   }

   private void setCustomerTotalPay(Customer customer) {
       System.out.println("Input Customer's Total Pay : ");
       try{
           String pay = nextLine(Message.END_MSG);
           customer.setTotalPay(Integer.parseInt(pay));
       } catch (InputEndException e){
           System.out.println(Message.ERR_MSG_INPUT_END);
       }
   }

   public void viewCustomer(){
       System.out.println("======== Customer Info. ========");
       for(int i=0; i<allCustomers.size(); i++){
           System.out.println(String.format("No. %d => ", i+1)+ allCustomers.get(i));
       }
   }

   private void updateCustomer() {
       try {
           viewCustomer();
           System.out.printf("Which customer ( 1 ~ %d )? ", allCustomers.size());
           Integer index = scanner.nextInt();
           scanner.nextLine();
           if(index <= 0 || index > allCustomers.size()){
               throw new InputRangeException();
           }
           Customer customer = allCustomers.get(index - 1);
           setCustomerManage(customer);
           allCustomers.refresh();
       } catch (InputRangeException e){
           System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
       }
   }

   private void deleteCustomer() {
       try {
           viewCustomer();
           System.out.printf("Which customer ( 1 ~ %d )? ", allCustomers.size());
           Integer index = scanner.nextInt();
           scanner.nextLine();
           if(index <= 0 || index > allCustomers.size()){
               throw new InputRangeException();
           }
           System.out.println(allCustomers.get(index - 1));
           allCustomers.pop(index - 1);
           viewCustomer();
       } catch (InputRangeException e){
           System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
       }
   }

}


