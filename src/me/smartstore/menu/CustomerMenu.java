package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

public class CustomerMenu implements Menu {

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

    public void refresh() {
        allCustomers.refresh(allGroups);
    }

    @Override
    public void manage() {
        while(true) {
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
            else break;
        }
    }

    public int addCustomerMenu() {
        while(true) {
            try {
                System.out.println();
                System.out.println("===============================");
                System.out.println(" 1. Customer Name");
                System.out.println(" 2. Customer ID");
                System.out.println(" 3. Customer Spent Time");
                System.out.println(" 4. Customer Total Pay");
                System.out.println(" 5. Back");
                System.out.println("===============================");
                System.out.print("Choose One: ");
                int choice = Integer.parseInt(nextLine());
                if (choice >= 1 && choice <= 5)
                    return choice;
                throw new InputRangeException();
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public void addCustomer() {
        int size = addSizeCustomer();
        for(int i=1; i<=size; i++) {
            System.out.println();
            System.out.printf("====== Customer %d Info. ======", i);
            Customer customer = new Customer();
            while(true) {
                int choice = addCustomerMenu();
                if (choice == 1) setNameCustomer(customer);
                else if (choice == 2) setIdCustomer(customer);
                else if (choice == 3) setSpentTimeCustomer(customer);
                else if (choice == 4) setTotalPayCustomer(customer);
                else break;
            }
            allCustomers.add(customer);
            refresh();
        }
    }

    public void deleteCustomer() {
        viewCustomer();
        if(allCustomers.size() != 0) {
            while(true) {
                try {
                    System.out.printf("Which customer ( 1 ~ %d )? ", allCustomers.size());
                    int choice = Integer.parseInt(nextLine());
                    if (choice >= 1 && choice <= allCustomers.size()) {
                        allCustomers.pop(choice-1);
                        break;
                    }
                    throw new InputRangeException();
                } catch (NumberFormatException e) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                } catch (InputRangeException e) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                }
            }
        }
    }

    public void updateCustomer() {
        viewCustomer();
        if(allCustomers.size() != 0) {
            while(true) {
                try {
                    System.out.printf("Which customer ( 1 ~ %d )? ", allCustomers.size());
                    int choice = Integer.parseInt(nextLine());
                    if (choice >= 1 && choice <= allCustomers.size()) {
                        updateSetCustomer(choice);
                        break;
                    }
                    throw new InputRangeException();
                } catch (NumberFormatException e) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                } catch (InputRangeException e) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                }
            }
        }
    }

    public void updateSetCustomer(int num) {
        Customer customer = allCustomers.get(num-1);
        while(true) {
            int choice = addCustomerMenu();
            if (choice == 1) setNameCustomer(customer);
            else if (choice == 2) setIdCustomer(customer);
            else if (choice == 3) setSpentTimeCustomer(customer);
            else if (choice == 4) setTotalPayCustomer(customer);
            else break;
        }
        refresh();
    }

    public void viewCustomer() {
        if(allCustomers.size() == 0) {
            System.out.println("No Customers. Please input one first.");
        }
        else {
            System.out.println("====== Customer Info. ======");
            for(int i=0; i<allCustomers.size(); i++) {
                System.out.printf("No. %d => " + allCustomers.get(i) + "\n", i+1);
            }
        }
    }



    public void setTotalPayCustomer(Customer customer) {
        while(true) {
            System.out.println("Input Customer's Total Pay: ");
            try {
                int totalPay = Integer.parseInt(nextLine(Message.END_MSG));
                customer.setTotalPay(totalPay);
                break;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }
    }

    public void setSpentTimeCustomer(Customer customer) {
        while(true) {
            System.out.println("Input Customer's Spent Time: ");
            try {
                int spentTime = Integer.parseInt(nextLine(Message.END_MSG));
                customer.setSpentTime(spentTime);
                break;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }
    }

    public void setIdCustomer(Customer customer) {
        while(true) {
            System.out.println("Input Customer's Id: ");
            try {
                String userId = nextLine(Message.END_MSG);
                if (userId == null || userId.equals(""))
                    throw new InputEmptyException();
                customer.setUserId(userId);
                break;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
            }
        }
    }

    public void setNameCustomer(Customer customer) {
        while(true) {
            System.out.println("Input Customer's Name: ");
            try {
                String name = nextLine(Message.END_MSG);
                if (name == null || name.equals(""))
                    throw new InputEmptyException();
                customer.setName(name);
                break;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
            }
        }
    }

    public int addSizeCustomer() {
        while(true) {
            try {
                System.out.println("How many customers to input?");
                int choice = Integer.parseInt(nextLine(Message.END_MSG));
                if(choice < 0)
                    throw new InputRangeException();
                return choice;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return 0;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

}
