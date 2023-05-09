package domain.menu.main.customer;

import domain.customer.Customer;
import domain.customer.Customers;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Scanner;

public interface UpdateCustomerExecute {
    static Runnable getMethod() {
        return () -> {
            Scanner scanner = InputScanner.get();
            Customers customerRepository = Customers.getInstance();

            customerRepository.viewCustomersInfo();

            System.out.print("Which customer ( 1 ~ " + customerRepository.size() + " )?");
            int customerNumber = Integer.parseInt(scanner.nextLine());
            Customer getCustomer = customerRepository.getByCustomerNumber(customerNumber);

            while (true) {
                System.out.println("==============================\n" +
                        " 1. Customer Name\n" +
                        " 2. Customer ID\n" +
                        " 3. Customer Spent Time\n" +
                        " 4. Customer Total Pay\n" +
                        " 5. Back\n" +
                        "==============================");
                OutputView.chooseMenu();
                int setInfoIndex = Integer.parseInt(scanner.nextLine());
                System.out.println(setInfoIndex);
                if (setInfoIndex >= 6 || setInfoIndex <= 0) {
                    System.out.println("잘못된 메뉴임 다시 입력하셈");
                    continue;
                }

                if (setInfoIndex == 1) {
                    System.out.println("Input Customer's Name:\n" +
                            "** Press 'end', if you want to exit! **\n");
                    getCustomer.setName(scanner.nextLine());
                    continue;
                }

                if (setInfoIndex == 2) {
                    System.out.println("Input Customer's ID:\n" +
                            "** Press 'end', if you want to exit! **\n");
                    getCustomer.setUserId(scanner.nextLine());
                    continue;
                }
                if (setInfoIndex == 3) {
                    System.out.println("Input Customer's Spent Time:\n" +
                            "** Press 'end', if you want to exit! **\n");
                    getCustomer.setSpentTime(Integer.parseInt(scanner.nextLine()));
                    continue;
                }
                if (setInfoIndex == 4) {
                    System.out.println("Input Customer's Total Pay:\n" +
                            "** Press 'end', if you want to exit! **\n");
                    getCustomer.setTotalPay(Integer.parseInt(scanner.nextLine()));
                    continue;
                }
                break;
            }

        };
    }
}
