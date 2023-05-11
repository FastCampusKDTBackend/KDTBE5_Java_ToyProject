package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.Menu;

import java.util.InputMismatchException;

public class UpdateCustomerConfirmMenu extends Menu {

    private static final String UPDATE_CUSTOMER_CONFIRM_OUTPUT =
                    '\n' +
                    "==== Update Customer Confirm ====" + '\n' +
                    " 1. " + "Yes (Update)" + '\n' +
                    " 2. " + "No (Back)" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private static class InstanceHolder {
        private static final UpdateCustomerConfirmMenu INSTANCE = new UpdateCustomerConfirmMenu();
    }
    private UpdateCustomerConfirmMenu() {}
    public static UpdateCustomerConfirmMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        final String updateBeforeAndAfterInfo = CustomerRepository.getInstance().getUpdateBeforeAndAfterInfo();
        while (true) {
            try {
                print(updateBeforeAndAfterInfo);
                print(UPDATE_CUSTOMER_CONFIRM_OUTPUT);
                int menuIdx = inputMenuIdx();
                return getNextMenu(menuIdx);
            } catch (InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(UpdateCustomerIntoRepositoryMenu.getInstance(), UpdateCustomerMenu.getInstance());
    }
}
