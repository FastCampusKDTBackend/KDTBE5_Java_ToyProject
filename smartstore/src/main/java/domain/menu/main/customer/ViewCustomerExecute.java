package domain.menu.main.customer;

import domain.customer.Customers;

public interface ViewCustomerExecute {
    static Runnable getMethod() {
        return () -> {
            Customers customerRepository = Customers.getInstance();
            customerRepository.viewCustomersInfo();
        };
    }
}
