package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.customer.InvalidCustomerNameException;
import me.smartstore.menu.exception.InputIsEndException;

import static me.smartstore.customer.Customer.NAME_FORMAT;

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
    protected String inputProperty() throws InputIsEndException {
        return inputStringOrEnd();
    }

    @Override
    protected void setTempProperty(Object property) throws InvalidCustomerNameException {
        CustomerRepository.getInstance().setTempName((String) property);
    }
}
