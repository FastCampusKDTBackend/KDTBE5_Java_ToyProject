package smartstore.menu;

import smartstore.customer.Customer;
import smartstore.customer.Customers;
import smartstore.exception.InputEndException;
import smartstore.exception.InputTypeException;
import smartstore.util.Message;

public class CustomerMenu implements Menu {
	
	private final Customers allCustomers = Customers.getInstance();
	
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
        	try {
        		int choice = chooseMenu(new String[]{
                        "Add Customer",
                        "View Customer",
                        "Update Customer",
                        "Delete Customer",
                        "Back"});
                
                if (choice == 1) addCustomer();
                else if (choice == 2) viewCustomer();
                else if (choice == 3) updateCustomer();
                else if (choice == 4) deleteCustomer();
                else break; // choice == 5
        	} catch (InputTypeException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
			}
        }
    }
    
    public void addCustomer() {
    	while ( true ) {
            try {
            	System.out.print("How many customers to input? ");
                String choice = nextLine(Message.END_MSG);
                System.out.println();
                
                int numberOfCustomers = Integer.parseInt(choice);
            	
            	for(int i = 0; i < numberOfCustomers; i++) {
            		System.out.println("====== Customer " + (i + 1) + " Info. ======\n");
            		Customer customer = new Customer();
            			
            		customer.manage();
            		allCustomers.add(customer);
                }
            	break;
            } catch (InputTypeException e) {
                	System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            } 
    	}
    }
    
    public void viewCustomer() {
    	
    }

	public void updateCustomer() {
	
	}
	
	public void deleteCustomer() {
    	
    }
	
	public static boolean isNumeric(String arg) {
    	for (int i = 0; i < arg.length(); i++) {
    		if (!Character.isDigit(arg.charAt(i))) {
    			return false;
    		}
    	}
    	return true;
    }
}
