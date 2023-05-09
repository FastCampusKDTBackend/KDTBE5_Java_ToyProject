package domain.menu.main.customer;

import domain.customer.Customer;
import domain.menu.Menu;
import util.common.exception.NotFoundException;
import util.view.InputScanner;
import util.view.OutputView;
import util.view.ViewMessage;

import java.util.Arrays;
import java.util.function.Consumer;

public enum ModifyCustomerInforationMenu implements Menu {
    MODIFY_NAME(
            1,
            "Customer Name",
            (customer) -> {
                OutputView.showMessage(
                        ViewMessage.INPUT_CUSTOMER_NAME
                );
                OutputView.showMessage(
                        ViewMessage.INPUT_END_FOR_EXIT
                );
                customer.setName(InputScanner.get().nextLine());
            }
    ),
    MODIFY_ID(
            2,
            "Customer ID",
            (customer) -> {
                OutputView.showMessage(
                        ViewMessage.INPUT_CUSTOMER_ID
                );
                OutputView.showMessage(
                        ViewMessage.INPUT_END_FOR_EXIT
                );
                customer.setUserId(InputScanner.get().nextLine());
            }
    ),
    MODIFY_SPENT_TIME(
            3,
            "Customer Spent Time",
            (customer) -> {
                OutputView.showMessage(
                        ViewMessage.INPUT_CUSTOMER_SPENT_TIME
                );
                OutputView.showMessage(
                        ViewMessage.INPUT_END_FOR_EXIT
                );
                customer.setSpentTime(
                        Integer.parseInt(InputScanner.get().nextLine())
                );
            }
    ),
    MODIFY_TOTAL_PAY(
            4,
            "Customer Total Pay",
            (customer) -> {
                OutputView.showMessage(
                        ViewMessage.INPUT_CUSTOMER_TOTAL_PAY
                );
                OutputView.showMessage(
                        ViewMessage.INPUT_END_FOR_EXIT
                );
                customer.setTotalPay(
                        Integer.parseInt(InputScanner.get().nextLine())
                );
            }
    );

    private final int menuNumber;
    private final String menuName;
    private final Consumer<Customer> consumer;

    ModifyCustomerInforationMenu(int menuNumber, String menuName, Consumer<Customer> consumer) {
        this.menuNumber = menuNumber;
        this.menuName = menuName;
        this.consumer = consumer;
    }

    public boolean isEqualMenuNumber(int menuNumber) {
        return this.menuNumber == menuNumber;
    }

    public void execute(Customer customer) {
        consumer.accept(customer);
    }

    public static void findMenuAndExecution(int menuNumber, Customer customer) {
        Arrays.stream(ModifyCustomerInforationMenu.values())
                .filter(subMenus -> subMenus.isEqualMenuNumber(menuNumber))
                .findFirst()
                .orElseThrow(NotFoundException::new)
                .execute(customer);
    }
}
