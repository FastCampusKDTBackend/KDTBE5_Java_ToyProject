package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.exception.InputIsEndException;

import java.util.InputMismatchException;

public class InputCustomerSpentHoursMenu extends InputCustomerPropertyMenu {

    private static final String SPENT_HOURS_INPUT =
                    "Input " + "Customer" + "'s " + "Spent Hours" + ':';

    private static class InstanceHolder {
        private static final InputCustomerSpentHoursMenu INSTANCE = new InputCustomerSpentHoursMenu();
    }
    private InputCustomerSpentHoursMenu() { super(SPENT_HOURS_INPUT); }
    public static InputCustomerSpentHoursMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected Object inputProperty() throws InputIsEndException, InputMismatchException {
        return inputZeroOrPositiveIntegerOrEnd();
    }

    @Override
    protected void setTempProperty(Object property) throws IllegalArgumentException, IllegalStateException {
        CustomerRepository.getInstance().setTempSpentHours((Integer) property);
    }
}
