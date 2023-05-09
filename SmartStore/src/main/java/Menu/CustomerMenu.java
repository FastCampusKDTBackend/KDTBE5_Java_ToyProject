package Menu;

import Arrays.Exceptions.EmptyArrayException;
import CustomException.InputEndException;
import CustomException.InputRangeException;
import CustomException.InputTypeException;
import CustomException.Message;
import Customer.Customer;
import Customer.Customers;
import Group.Group;

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
            else if (choice == 3) updateCustomerData(3);
            else if (choice == 4) updateCustomerData(4);
            else if (choice == 5) break;
        }
    }

    private void addCustomerData() {
        while (true) {
            try {
                System.out.println("How many customer to input?");
                int customers = Integer.parseInt(nextLine(Message.END_MSG));
                //등록 할 고객 수 만큼 반복
                for (int i = 0; i < customers; i++) {
                    System.out.println("\n====== Customer " + (i + 1) + " Info. ======\n");
                    Customer setJoiner = new Customer();
                    //setCustomerData 메소드로 사용자 정보 추가
                    setJoiner = setCustomerData(setJoiner);
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

    private Customer setCustomerData(Customer joiner) {
        while (true) {
            try {
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
        try {
            allCustomers.refresh();
            System.out.println("\n======= Customer Info. =======");
            for (int i = 0; i < allCustomers.size(); i++) {
                System.out.print("No. " + (i + 1) + "=> ");
                System.out.println(allCustomers.get(i));
            }
        } catch (EmptyArrayException e) {
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
        }

    }

    //수정 + 삭제
    private void updateCustomerData(int choice) {
        viewCustomerData();
        try {
            System.out.print("Which customer ( 1 ~ " + allCustomers.size() + " )? ");
            int customerNum = Integer.parseInt(nextLine());
            if (choice == 3) {
                //choice가 3이면 수정
                setCustomerData((Customer) allCustomers.get(customerNum - 1));
            } else if (choice == 4) {
                //choice가 4면 삭제
                System.out.println(allCustomers.pop(customerNum - 1));
                viewCustomerData();
            }
        } catch (InputMismatchException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
        }
    }
    // TODO: 2023-05-03, 예외처리
}

