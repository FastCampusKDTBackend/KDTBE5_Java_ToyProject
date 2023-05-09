package domain.menu.main.customer;

import domain.customer.Customer;
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

    private static void run() {
        if (!GroupType.isGroupParameterSet()) {
            OutputView.showErrorMessage(ErrorMessage.SET_PARAMETER);
            return;
        }

        OutputView.showMessage(ViewMessage.INPUT_CUSTOMER_NUMBER);
        OutputView.showExitGuide();

        int inputCustomerSize = getInputNumber();

        IntStream.range(0, inputCustomerSize).forEach(__ -> modifyCustomerInfomation(new Customer()));
    }
}
