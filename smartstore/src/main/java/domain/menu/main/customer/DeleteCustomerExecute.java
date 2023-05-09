package domain.menu.main.customer;

import domain.customer.Customers;
import util.view.InputScanner;

import java.util.Scanner;

public interface DeleteCustomerExecute {
    static Runnable getMethod() {
        return () -> {
            Scanner scanner = InputScanner.get();
            Customers customerRepository = Customers.getInstance();

            customerRepository.viewCustomersInfo();
            System.out.print("Which customer ( 1 ~ " + customerRepository.size() + " )?");

            int customerNumber = Integer.parseInt(scanner.nextLine());

            customerRepository.deleteByCustomerNumber(customerNumber);
        };
    }
}
