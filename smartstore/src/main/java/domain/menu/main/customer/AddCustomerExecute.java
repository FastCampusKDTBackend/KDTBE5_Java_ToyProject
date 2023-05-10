package domain.menu.main.customer;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.GroupType;
import util.common.ErrorMessage;
import util.common.exception.NotFoundException;
import util.view.InputScanner;
import util.view.OutputView;
import util.view.ViewMessage;

import java.util.stream.IntStream;

public interface AddCustomerExecute {
    static Runnable getMethod() {
        return AddCustomerExecute::run;
    }

    private static void run() {
        if (!GroupType.isGroupParameterSet()) {
            OutputView.showErrorMessage(ErrorMessage.SET_PARAMETER);
            return;
        }

        OutputView.showMessage(ViewMessage.INPUT_CUSTOMER_NUMBER);
        OutputView.showExitGuide();

        int inputCustomerSize = getInputNumber();

        IntStream.range(0, inputCustomerSize).forEach(__ -> {
            Customer newCustomer = new Customer();
            modifyCustomerInfomation(newCustomer);
            Customers.getInstance().save(newCustomer);
        });
    }

    private static void modifyCustomerInfomation(Customer customer) {
        while (true) {
            OutputView.showMenus(ModifyCustomerInforationMenu.values());
            OutputView.showMessage(ViewMessage.INPUT_MENU);
            try {
                int menuNumber = getInputNumber();
                if (ModifyCustomerInforationMenu.isQuit(menuNumber)) {
                    return;
                }
                ModifyCustomerInforationMenu.findMenuAndExecution(menuNumber, customer);
            } catch (NotFoundException | NumberFormatException exception) {
                OutputView.showErrorMessage(exception.getMessage());
            }
        }
    }

    private static int getInputNumber() {
        while (true) {
            try {
                String inputNumber = InputScanner.get().nextLine();
                if (ViewMessage.isExit(inputNumber)) {
                    return 0;
                }
                return Integer.parseInt(inputNumber);
            } catch (RuntimeException runtimeException) {
                OutputView.showErrorMessage(ErrorMessage.INVALID_INPUT);
            }
        }
    }
}
