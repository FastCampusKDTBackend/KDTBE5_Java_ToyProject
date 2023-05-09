package domain.menu.main.customer;

import domain.customer.Customer;
import domain.customer.Customers;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Scanner;

public interface AddCustomerExecute {
    static Runnable getMethod() {
        return () -> {
            Scanner scanner = InputScanner.get();
            Customers customerRepository = Customers.getInstance();

            while (true) {
                System.out.println("How many customers to input?");
                OutputView.viewExitGuide();

                int inputCustomerSize = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < inputCustomerSize; i++) {
                    Customer newCustomer = new Customer();

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
                            newCustomer.setName(scanner.nextLine());
                            continue;
                        }

                        if (setInfoIndex == 2) {
                            System.out.println("Input Customer's ID:\n" +
                                    "** Press 'end', if you want to exit! **\n");
                            newCustomer.setUserId(scanner.nextLine());
                            continue;
                        }
                        if (setInfoIndex == 3) {
                            System.out.println("Input Customer's Spent Time:\n" +
                                    "** Press 'end', if you want to exit! **\n");
                            newCustomer.setSpentTime(Integer.parseInt(scanner.nextLine()));
                            continue;
                        }
                        if (setInfoIndex == 4) {
                            System.out.println("Input Customer's Total Pay:\n" +
                                    "** Press 'end', if you want to exit! **\n");
                            newCustomer.setTotalPay(Integer.parseInt(scanner.nextLine()));
                            continue;
                        }
                        if (setInfoIndex == 5) {
                            customerRepository.save(newCustomer);
                            break;
                        }

                    }




                }
                return;

            }
        };
    }
}
