package me.smartstore.domain.menu;

import me.smartstore.domain.customer.Customer;
import me.smartstore.domain.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.exception.message.ErrorMessage;

import java.util.InputMismatchException;

import static me.smartstore.exception.message.ErrorMessage.*;

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
            int choice = chooseMenu(new String[]{
                "Add Customer Data",
                "View Customer Data",
                "Update Customer Data",
                "Delete Customer Data",
                "Back"
            });

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
        }
    }

    // TODO: Customer Methods
    private void createCustomer() {
        while (true) {
            System.out.println("How many customers to input?");
            try {
                String inputData = nextLine("END");
                Integer number = convertInt(inputData);
                // 최대 등록 인원 지정 조건이 없어서 100명으로 지정
                if (number <= 0 || number > 100) {
                    throw new InputRangeException();
                }

                for (int i = 1; i <= number; i++) {
                    System.out.println(String.format("====== Customer %d Info. ======\n", i));
                    Customer customer = new Customer();
                    boolean isNotSaved = true;
                    while (isNotSaved) {
                        int choice = chooseMenu(new String[] {
                                "Customer Name",
                                "Customer ID",
                                "Customer Spent Time",
                                "Customer Total Pay",
                                "Save"
                        });

                        try {
                            if (choice == 1) {
                                System.out.println("\nInput Customer's Name:");
                                customer.setCustomerName(nextLine("END"));
                            } else if (choice == 2) {
                                System.out.println("\nInput Customer's ID:");
                                customer.setCustomerId(nextLine("END"));
                            } else if (choice == 3) {
                                while (true) {
                                    try {
                                        System.out.println("\nInput Customer's Spent Time:");
                                        Integer usageTime = convertInt(nextLine("END"));
                                        if (usageTime < 0) {
                                            throw new InputRangeException();
                                        }
                                        customer.setTotalUsageTime(usageTime);
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
                                        Integer purchaseAmount = convertInt(nextLine("END"));
                                        if (purchaseAmount < 0) {
                                            throw new InputRangeException();
                                        }
                                        customer.setTotalUsageTime(purchaseAmount);
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println(ERR_MSG_INVALID_INPUT_TYPE.getMessage());
                                    } catch (InputRangeException e) {
                                        System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
                                    }
                                }
                            } else {
                                allCustomers.add(customer);
                                isNotSaved = false;
                            }
                        } catch (InputEndException e) {
                            System.out.println(ERR_MSG_INPUT_END.getMessage());
                        }
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_TYPE.getMessage());
            } catch (InputRangeException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
            } catch (InputEndException e) {
                System.out.println(ERR_MSG_INPUT_END.getMessage());
                break;
            }

            return;
        }
    }

    private void findAllCustomers() {}

    private void updateCustomer() {}

    private void deleteCustomer() {}
}
