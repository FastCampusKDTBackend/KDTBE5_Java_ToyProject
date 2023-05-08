package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.Menu;
import me.smartstore.menu.exception.InputIsEndException;
import me.smartstore.menu.topic.CustomerMenu;

import java.util.InputMismatchException;

public abstract class InputCustomerPropertyMenu extends Menu {

    private final String PROPERTY_INPUT;

    InputCustomerPropertyMenu(String msg) {
        PROPERTY_INPUT = msg + '\n' + END_INPUT;
    }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        if (getPrevMenu() == UpdateCustomerMenu.getInstance())
            CustomerRepository.getInstance().resetTempCustomer();
        inputPropertyAndSetItIntoTemp();
        return getBackMenu();
    }

    @Override
    protected void setNextMenus() {}

    protected void inputPropertyAndSetItIntoTemp() {
        while (true) {
            print(PROPERTY_INPUT);
            try {
                Object property = inputProperty();
                setTempProperty(property);
                return;
            } catch (InputIsEndException | InputMismatchException |
                     IllegalArgumentException | IllegalStateException e) {
                print(e.getMessage());
                if (e instanceof InputIsEndException)
                    return;
            }
        }
    }

    protected abstract Object inputProperty() throws InputIsEndException, InputMismatchException;

    protected abstract void setTempProperty(Object property) throws IllegalArgumentException, IllegalStateException;
}
