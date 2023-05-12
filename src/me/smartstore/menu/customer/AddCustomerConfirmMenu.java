package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.AddCustomerMenu;

import java.util.InputMismatchException;

public class AddCustomerConfirmMenu extends Menu {

    private static final String ADD_CUSTOMER_CONFIRM_OUTPUT =
                    '\n' +
                    "==== Add Customer Confirm ====" + '\n' +
                    " 1. " + "Yes (Add)" + '\n' +
                    " 2. " + "No (Back)" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private static class InstanceHolder {
        private static final AddCustomerConfirmMenu INSTANCE = new AddCustomerConfirmMenu();
    }
    private AddCustomerConfirmMenu() { super(); }
    public static AddCustomerConfirmMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        String tempCustomerInfo = getTempCustomerInfo();
        while (true) {
            try {
                print('\n' + tempCustomerInfo);
                if (isTempCustomerIdNull()) {
                    print("\nID input must be required.\n");
                    return getBackMenu();
                }
                print(ADD_CUSTOMER_CONFIRM_OUTPUT);
                int menuIdx = inputMenuIdx();
                return getNextMenu(menuIdx);
            } catch (InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(AddCustomerIntoRepositoryMenu.getInstance(), AddCustomerMenu.getInstance());
    }

    private String getTempCustomerInfo() {
        return CustomerRepository.getInstance().getTempInfo();
    }

    private boolean isTempCustomerIdNull() {
        return CustomerRepository.getInstance().isTempIdNull();
    }
}
