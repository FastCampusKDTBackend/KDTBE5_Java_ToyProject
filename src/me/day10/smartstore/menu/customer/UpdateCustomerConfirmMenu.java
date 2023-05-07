package me.day10.smartstore.menu.customer;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.exception.InvalidMenuException;

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
    private UpdateCustomerConfirmMenu() { super(); }
    public static UpdateCustomerConfirmMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        final String updateBeforeAndAfterInfo = CustomerRepository.getInstance().getUpdateBeforeAndAfterInfo();
        while (true) {
            try {
                print(updateBeforeAndAfterInfo);
                print(UPDATE_CUSTOMER_CONFIRM_OUTPUT);
                int i = inputMenu();
                return nextMenus[i];
            } catch (InputMismatchException | InvalidMenuException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, UpdateCustomerIntoRepositoryMenu.getInstance(), UpdateCustomerMenu.getInstance());
    }
}
