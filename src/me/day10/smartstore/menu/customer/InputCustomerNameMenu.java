package me.day10.smartstore.menu.customer;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.customer.InvalidCustomerNameException;
import me.day10.smartstore.menu.exception.InputIsEndException;
import me.day10.smartstore.menu.topic.AddCustomerMenu;

import static me.day10.smartstore.customer.Customer.NAME_FORMAT;

public class InputCustomerNameMenu extends InputCustomerPropertyMenu {

    private static final String NAME_INPUT =
                    "Input " + "Customer" + "'s " + "Name" + ":\n" +
                    NAME_FORMAT;

    private static class InstanceHolder {
        private static final InputCustomerNameMenu INSTANCE = new InputCustomerNameMenu();
    }
    private InputCustomerNameMenu() { super(NAME_INPUT); }
    public static InputCustomerNameMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, AddCustomerMenu.getInstance());
    }

    @Override
    protected String inputProperty() throws InputIsEndException {
        return inputStringOrEnd();
    }

    @Override
    protected void setTempProperty(Object property) throws InvalidCustomerNameException {
        CustomerRepository.getInstance().setTempName((String) property);
    }
}
