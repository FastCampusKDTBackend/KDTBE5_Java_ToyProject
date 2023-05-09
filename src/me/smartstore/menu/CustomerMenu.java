package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.ArrayEmptyException;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.exception.constant.Message;

import java.util.InputMismatchException;

public class CustomerMenu implements Menu{
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
    public void manage(){
        while (true) {
            try {
                int choice = chooseMenu(new String[]{
                        "Add Customer Data",
                        "View Customer Data",
                        "Update Customer Data",
                        "Delete Customer Data",
                        "Back"
                });

                if (choice ==  1) {
                    int size = 0;
                    size = getCustomerSize();
                    addCustomer(size);
                } else if (choice == 2) {
                    viewCustomer();
                } else if (choice == 3) {
                    updateCustomer();
                } else if (choice == 4) {
                    deleteCustomer();
                } else {
                    break;
                }

            } catch (NullPointerException e) {
                break;
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT.getMessage());
            }
        }
    }

    public int getCustomerSize() {
        while (true) {
            try {
                System.out.println("How many customers to input? ");
                int size = Integer.parseInt(nextLine("END"));
                if (size < 0) {
                    throw new InputRangeException();
                }
                return size;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT.getMessage());
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return -1;
            }
        }
    }

    public void addCustomer(int size) {
        for (int i = 0; i < size; i++) {
            Customer customer = new Customer();
            System.out.println("\n====== Customer " + (i + 1) + " Info. ======");

            while (true) {
                int choice = chooseMenu(new String[]{
                        "Customer Name",
                        "Customer ID",
                        "Customer Spent Time",
                        "Customer Total Pay",
                        "Back"
                });

                if (choice == 1) {
                    setCustomerName(customer);
                } else if (choice == 2) {
                    setCustomerId(customer);
                } else if (choice == 3) {
                    setCustomerSpentTime(customer);
                } else if (choice == 4) {
                    setCustomerTotalPay(customer);
                } else {
                    break;
                }
            }
            allCustomers.add(customer);
            allCustomers.refresh(customer);
        }
    }

    public void viewCustomer() {
        if (this.allCustomers.size() == 0) {
            System.out.println("No Customers. Please input one first.");
        }

        System.out.println("\n======= Customer Info. =======");

        for (int i = 0; i < this.allCustomers.size(); i++) {
            Customer customer = this.allCustomers.get(i);

            if (customer != null) {
                System.out.println("No. " + (i + 1) + " => " + customer);
            } else {
                System.out.println("null");
            }
        }
    }

    public void updateCustomer() {
        viewCustomer();
        int customerNum = 0;

        try {
            customerNum = findCustomer();
        } catch (ArrayEmptyException e) {
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT.getMessage());
        } catch (InputEndException e) {
            System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
        }

        Customer customer = this.allCustomers.get(customerNum - 1);

        while (true) {
            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"
            });

            if (choice == 1) {
                setCustomerName(customer);
            } else if (choice == 2) {
                setCustomerId(customer);
            } else if (choice == 3) {
                setCustomerSpentTime(customer);
            } else if (choice == 4) {
                setCustomerTotalPay(customer);
            } else {
                break;
            }
        }
        allCustomers.refresh(allCustomers.get(customerNum));
    }

    public void deleteCustomer() {
        viewCustomer();
        int customerNum = 0;

        try {
            customerNum = findCustomer();
        } catch (ArrayEmptyException e) {
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT.getMessage());
        } catch (InputEndException e) {
            System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
            return;
        }

        Customer customer = this.allCustomers.get(customerNum - 1);
        System.out.println(customer);
        this.allCustomers.pop(customerNum - 1);
        viewCustomer();
    }

    public void setCustomerName(Customer customer) {
        while (true) {
            try {
                System.out.print("\nInput Customer's Name: ");
                String name = nextLine("END");
                if (name == null || name.equals("")) {
                    throw new InputEmptyException();
                }
                customer.setCustomerName(name);
                return;
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }

    public void setCustomerId(Customer customer) {
        while (true) {
            try {
                System.out.print("\nInput Customer's ID: ");
                String customerId = nextLine("END");
                if (customerId == null || customerId.equals("")) {
                    throw new InputEmptyException();
                }
                customer.setCustomerId(customerId);
                return;
            } catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE.getMessage());
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
                break;
            }
        }
    }

    public void setCustomerSpentTime(Customer customer) {
        while (true) {
            try {
                System.out.print("\nInput Customer's Spent Time: ");
                int spentTime = Integer.parseInt(nextLine("END"));
                if (spentTime < 0) {
                    throw new InputRangeException();
                }
                customer.setCustomerTotalTime(spentTime);
                break;
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
                break;
            }
        }
    }

    public void setCustomerTotalPay(Customer customer) {
        while (true) {
            try {
                System.out.print("\nInput Customer's Total Payment: ");
                int totalPay = Integer.parseInt(nextLine("END"));
                if (totalPay < 0) {
                    throw new InputRangeException();
                }
                customer.setCustomerTotalPay(totalPay);
                return;
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END.getMessage());
                break;
            }
        }
    }

    public int findCustomer() throws ArrayEmptyException, InputEndException {
        int size = this.allCustomers.size();
        if (size == 0) {
            throw new ArrayEmptyException();
        }
        while (true) {
            try {
                System.out.println("\nWhich customer ( 1 ~ " + size + " )? ");
                int customerNum = Integer.parseInt(nextLine());
                if (customerNum < 1 || customerNum > size) {
                    throw new InputRangeException();
                }
                return customerNum;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
}
