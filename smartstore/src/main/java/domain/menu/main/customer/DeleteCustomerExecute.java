package domain.menu.main.customer;

import domain.customer.Customers;
import domain.group.GroupType;
import util.common.ErrorMessage;
import util.common.exception.NotFoundException;
import util.view.InputScanner;
import util.view.OutputView;

public interface DeleteCustomerExecute {
    static Runnable getMethod() {
        return DeleteCustomerExecute::run;
    }

    private static void run() {
        Customers customerRepository = Customers.getInstance();

        if (!GroupType.isGroupParameterSet()) {
            OutputView.showErrorMessage(ErrorMessage.SET_PARAMETER);
            return;
        }

        try {
            customerRepository.viewCustomersInfo();
        } catch (NotFoundException notFoundException) {
            OutputView.showErrorMessage(notFoundException.getMessage());
            return;
        }

        while (true) {
            try {
                System.out.print("Which customer ( 1 ~ " + customerRepository.size() + " )?");
                int customerNumber = Integer.parseInt(InputScanner.get().nextLine());
                customerRepository.deleteByCustomerNumber(customerNumber);
                break;
            } catch (NumberFormatException numberFormatException) {
                OutputView.showErrorMessage(numberFormatException.getMessage());
            }

        }


    }
}
