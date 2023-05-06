package me.day10.smartstore.menu.customer;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.customer.DuplicateCustomerIdException;
import me.day10.smartstore.customer.InvalidCustomerIdException;
import me.day10.smartstore.menu.exception.InputIsEndException;

import static me.day10.smartstore.customer.Customer.ID_FORMAT;

public class InputCustomerIdMenu extends InputCustomerPropertyMenu {

    private static final String ID_INPUT =
                    "Input " + "Customer" + "'s " + "ID" + ":\n" +
                    ID_FORMAT;

    private static class InstanceHolder {
        private static final InputCustomerIdMenu INSTANCE = new InputCustomerIdMenu();
    }
    private InputCustomerIdMenu() { super(ID_INPUT); }
    public static InputCustomerIdMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected Object inputProperty() throws InputIsEndException {
        return inputStringOrEnd();
    }

    @Override
    protected void setTempProperty(Object property) throws InvalidCustomerIdException, DuplicateCustomerIdException {
        CustomerRepository.getInstance().setTempId((String) property);
    }
}
