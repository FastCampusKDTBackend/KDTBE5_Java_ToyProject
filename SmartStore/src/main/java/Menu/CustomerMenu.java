package Menu;

import CustomException.InputEndException;
import CustomException.InputRangeException;
import CustomException.InputTypeException;
import CustomException.Message;
import Customer.Customer;
import Customer.Customers;

import java.util.Arrays;
import java.util.InputMismatchException;

public class CustomerMenu implements Menu {

    private final Customers allCustomers = Customers.getInstance();
    private static CustomerMenu allCustomerMenu;

    public static CustomerMenu getInstance() {
        if (allCustomerMenu == null) {
            allCustomerMenu = new CustomerMenu();
        }
        return allCustomerMenu;
    }

    private CustomerMenu() {

    }

    @Override
    public void manage() {
        while (true) {
            int choice = allCustomerMenu.chooseMenu(new String[]{
                    "Add Customer Data",
                    "View Customer Data",
                    "Update Customer Data",
                    "Delete Customer Data",
                    "Back"
            });

            if (choice == 1) addCustomerData();
            else if (choice == 2) viewCustomerData();
            else if (choice == 3) updateCustomerData();
            else if (choice == 4) deleteCustomerData();
            else if (choice == 5) break;
        }
    }

    private void addCustomerData() {
        while (true) {
            try {
                System.out.println("How many customer to input?");
                int customers = Integer.parseInt(nextLine(Message.END_MSG));
                //등록 할 고객 수 만큼 반복
                for (int i = 1; i <= customers; i++) {
                    Customer setJoiner = new Customer();
                    //setCustomerData 메소드로 사용자 정보 추가
                    setJoiner = setCustomerData(i, setJoiner);
                    allCustomers.add(setJoiner);
                }
                return;

            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }
    }

    private Customer setCustomerData(int rep, Customer joiner) {
        while (true) {
            try {
                System.out.println("\n====== Customer " + rep + " Info. ======\n");
                int choice = allCustomerMenu.chooseMenu(new String[]{
                        "Customer Name",
                        "Customer ID",
                        "Customer Spent Time",
                        "Customer Total Pay",
                        "Back"
                });

                if (choice == 1) {
                    System.out.println("Input Customer's Name:");
                    joiner.setCtmName(nextLine(Message.END_MSG));
                } else if (choice == 2) {
                    System.out.println("Input Customer's ID:");
                    joiner.setCtmId(nextLine(Message.END_MSG));
                } else if (choice == 3) {
                    System.out.println("Input Customer's Spent Time:");
                    joiner.setTotalTime(Integer.valueOf(nextLine(Message.END_MSG)));
                } else if (choice == 4) {
                    System.out.println("Input Customer's Total Payment:");
                    joiner.setTotalPay(Integer.valueOf(nextLine(Message.END_MSG)));
                } else if (choice == 5) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            }
        }
        return joiner;
    }

    private void viewCustomerData() {
        System.out.println("\n======= Customer Info. =======");
        for (int i = 1; i <= allCustomers.size(); i++) {
            System.out.print("No. " + i + "=> ");
            System.out.println(allCustomers.get(i));
        }
    }

    private void updateCustomerData() {
        System.out.println("updateCustomerData");
    }

    private void deleteCustomerData() {
        System.out.println("deleteCustomerData");
    }

    // TODO: 2023-05-03, 예외처리
}

