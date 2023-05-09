package domain.menu.main.customer;

import domain.customer.Customer;
import domain.customer.Customers;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Scanner;

public interface CustomerMenuExecute {

    static <T> Runnable getMethod(int menuIndex) {
        Runnable[] methods = new Runnable[]{
                getAddCustomerExecuteMethod(),
                getViewCustomerExecuteMethod(),
                getUpdateCustomerExecuteMethod(),
                getDeleteCustomerExecuteMethod(),
        };
        return methods[menuIndex];
    }

    static Runnable getDeleteCustomerExecuteMethod() {
        return null;
    }

    static Runnable getUpdateCustomerExecuteMethod() {
        return null;
    }

    static Runnable getViewCustomerExecuteMethod() {
        return null;
    }

    private static Runnable getAddCustomerExecuteMethod() {
        return () -> {
            Scanner scanner = InputScanner.get();
            Customers customerRepository = Customers.getInstance();

            while (true) {
                System.out.println("How many customers to input?");
                OutputView.viewExitGuide();

                int inputCustomerSize = scanner.nextInt();

                for (int i = 0; i < inputCustomerSize; i++) {
                    Customer newCustomer = new Customer();

                    int setInfoIndex = scanner.nextInt();

                    if (setInfoIndex == 5) {
                        continue;
                    }

                    if (setInfoIndex == 1) {

                    }
                }
            }
        };
    }
}
