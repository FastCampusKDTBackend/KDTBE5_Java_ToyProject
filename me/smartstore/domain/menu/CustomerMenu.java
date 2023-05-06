package me.smartstore.domain.menu;

import me.smartstore.domain.customer.Customer;
import me.smartstore.domain.customer.Customers;
import me.smartstore.utils.exception.ArrayEmptyException;
import me.smartstore.utils.exception.InputEndException;
import me.smartstore.utils.exception.InputRangeException;

import java.util.InputMismatchException;

import static me.smartstore.utils.constant.Choice.CUSTOMER_MENU;
import static me.smartstore.utils.constant.Choice.CUSTOMER_MENU_SET;
import static me.smartstore.utils.constant.Message.*;

public class CustomerMenu implements Menu {
    private static CustomerMenu customerMenu;
    private final Customers allCustomers;

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {
        this.allCustomers = Customers.getInstance();
    }

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(CUSTOMER_MENU.getChoices());

            try {
                if (choice == 1) {
                    createCustomer();
                } else if (choice == 2) {
                    findAllCustomers();
                } else if (choice == 3) {
                    updateCustomer();
                } else if (choice == 4) {
                    deleteCustomer();
                } else {
                    break;
                }
            } catch (ArrayEmptyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createCustomer() {
        while (true) {
            System.out.println("How many customers to input?");
            try {
                String inputData = nextLine(END_MSG.getMessage());
                Integer number = convertInt(inputData);
                if (number <= 0 || number > 100) {
                    throw new InputRangeException();
                }

                for (int i = 1; i <= number; i++) {
                    System.out.println(String.format("====== Customer %d Info. ======", i));
                    Customer customer = new Customer();
                    setCustomerInfo(customer);
                    allCustomers.add(customer);
                }
                allCustomers.refresh();
                return;
            } catch (InputMismatchException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_TYPE.getMessage());
            } catch (InputRangeException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputEndException e) {
                System.out.println(ERR_MSG_INPUT_END.getMessage());
                break;
            }
        }
    }

    private void findAllCustomers() {
        if (allCustomers.size() == 0) {
            throw new ArrayEmptyException();
        }
        System.out.println("\n======= Customer Info. =======");
        for (int i = 0; i < allCustomers.size(); i++) {
            System.out.println(String.format("No. %d => ", i + 1) + allCustomers.get(i));
        }
    }

    private void updateCustomer() {
        findAllCustomers();
        while (true) {
            System.out.print(String.format("\nWhich customer ( 1 ~ %d )? ", allCustomers.size()));
            try {
                Integer index = convertInt(nextLine());
                if (index < 1 || index > allCustomers.size()) {
                    throw new InputRangeException();
                }
                Customer customer = allCustomers.get(index - 1);
                setCustomerInfo(customer);
                allCustomers.refresh();
                return;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void deleteCustomer() {
        findAllCustomers();
        while (true) {
            System.out.print(String.format("\nWhich customer ( 1 ~ %d )? ", allCustomers.size()));
            try {
                Integer index = convertInt(nextLine());
                if (index < 1 || index > allCustomers.size()) {
                    throw new InputRangeException();
                }
                System.out.println(allCustomers.pop(index - 1));
                findAllCustomers();
                return;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setCustomerInfo(Customer customer) {
        boolean isNotSaved = true;
        while (isNotSaved) {
            int choice = chooseMenu(CUSTOMER_MENU_SET.getChoices());

            try {
                if (choice == 1) {
                    System.out.println("\nInput Customer's Name:");
                    customer.setCustomerName(nextLine(END_MSG.getMessage()));
                } else if (choice == 2) {
                    System.out.println("\nInput Customer's ID:");
                    customer.setCustomerId(nextLine(END_MSG.getMessage()));
                } else if (choice == 3) {
                    while (true) {
                        try {
                            System.out.println("\nInput Customer's Spent Time:");
                            Integer usageTime = convertInt(nextLine(END_MSG.getMessage()));
                            if (usageTime < 0) {
                                throw new InputRangeException();
                            }
                            customer.setSpentTime(usageTime);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println(ERR_MSG_INVALID_INPUT_TYPE.getMessage());
                        } catch (InputRangeException e) {
                            System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
                        }
                    }
                } else if (choice == 4) {
                    while (true) {
                        try {
                            System.out.println("\nInput Customer's Total Payment:");
                            Integer purchaseAmount = convertInt(nextLine(END_MSG.getMessage()));
                            if (purchaseAmount < 0) {
                                throw new InputRangeException();
                            }
                            customer.setTotalPay(purchaseAmount);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println(ERR_MSG_INVALID_INPUT_TYPE.getMessage());
                        } catch (InputRangeException e) {
                            System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
                        }
                    }
                } else {
                    isNotSaved = false;
                }
            } catch (InputEndException e) {
                System.out.println(ERR_MSG_INPUT_END.getMessage());
            }
        }
    }
}
