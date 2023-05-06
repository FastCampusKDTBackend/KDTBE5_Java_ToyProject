package me.day10.smartstore.menu.customer;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.customer.InvalidCustomerNameException;
import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.exception.InputIsEndException;
import me.day10.smartstore.menu.topic.AddCustomerMenu;

import static me.day10.smartstore.customer.Customer.NAME_FORMAT;

public class InputCustomerNameMenu extends Menu {

    private static final String NAME_INPUT =
                    "Input " + "Customer" + "'s " + "Name" + ":\n" +
                    NAME_FORMAT + '\n' +
                    END_INPUT;

    private static class InstanceHolder {
        private static final InputCustomerNameMenu INSTANCE = new InputCustomerNameMenu();
    }
    private InputCustomerNameMenu() { super(); }
    public static InputCustomerNameMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        while (true) {
            print(NAME_INPUT);
            try {
                String name = inputCustomerNameOrEnd();
                CustomerRepository.getInstance().setTempName(name);
                return getBackMenu();
            } catch (InputIsEndException e) {
                print(e.getMessage());
                return getBackMenu();
            } catch (InvalidCustomerNameException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, AddCustomerMenu.getInstance());
    }

    private String inputCustomerNameOrEnd() throws InputIsEndException {
        return inputStringOrEnd();
    }
}
