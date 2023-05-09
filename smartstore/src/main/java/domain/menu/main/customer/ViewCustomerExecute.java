package domain.menu.main.customer;

import domain.customer.Customers;
import util.common.exception.NotFoundException;
import util.view.OutputView;

public interface ViewCustomerExecute {
    static Runnable getMethod() {
        return ViewCustomerExecute::run;
    }

    private static void run() {
        Customers customerRepository = Customers.getInstance();
        try {
            customerRepository.viewCustomersInfo();
        } catch (NotFoundException notFoundException) {
            OutputView.showErrorMessage(notFoundException.getMessage());
        }
    }
}
