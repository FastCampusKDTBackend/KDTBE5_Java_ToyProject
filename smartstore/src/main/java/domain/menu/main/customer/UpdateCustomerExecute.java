package domain.menu.main.customer;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.GroupType;
import util.common.ErrorMessage;
import util.common.exception.NotFoundException;
import util.view.InputScanner;
import util.view.OutputView;
import util.view.ViewMessage;

public interface UpdateCustomerExecute {
    static Runnable getMethod() {
        return UpdateCustomerExecute::run;
    }

    private static void run() {
        Customers customerRepository = Customers.getInstance();

        if (!GroupType.isGroupParameterSet()) {
            return;
        }

        try {
            customerRepository.viewCustomersInfo();
        } catch (NotFoundException notFoundException) {
            OutputView.showErrorMessage(notFoundException.getMessage());
            return;
        }

        OutputView.showSelectCustomerMessage(customerRepository.size());

        int customerNumber = getInputNumber();
        Customer customer = customerRepository.getByCustomerNumber(customerNumber);

        modifyCustomerInfomation(customer);

    }

    private static void modifyCustomerInfomation(Customer customer) {
        while (true) {
            OutputView.showMenus(ModifyCustomerInforationMenu.values());
            OutputView.showMessage(ViewMessage.INPUT_MENU);
            try {
                ModifyCustomerInforationMenu.findMenuAndExecution(getInputNumber(), customer);
                break;
            } catch (NotFoundException | NumberFormatException exception) {
                OutputView.showErrorMessage(exception.getMessage());
            }
        }
    }

    private static int getInputNumber() {
        while (true) {
            try {
                return Integer.parseInt(InputScanner.get().nextLine());
            } catch (RuntimeException runtimeException) {
                OutputView.showErrorMessage(ErrorMessage.INVALID_INPUT);
            }
        }
    }
}
