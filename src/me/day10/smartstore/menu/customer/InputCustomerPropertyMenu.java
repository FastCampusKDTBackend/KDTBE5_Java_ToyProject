package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.exception.InputIsEndException;
import me.day10.smartstore.menu.topic.AddCustomerMenu;

import java.util.InputMismatchException;

public abstract class InputCustomerPropertyMenu extends Menu {

    private final String PROPERTY_INPUT;

    InputCustomerPropertyMenu(String msg) {
        PROPERTY_INPUT = msg + '\n' + END_INPUT;
    }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        inputPropertyAndSetItIntoTemp();
        return getBackMenu();
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, AddCustomerMenu.getInstance());
    }

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
