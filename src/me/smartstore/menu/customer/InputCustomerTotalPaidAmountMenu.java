package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.exception.InputIsEndException;

import java.util.InputMismatchException;

public class InputCustomerTotalPaidAmountMenu extends InputCustomerPropertyMenu {

    private static final String TOTAL_PAID_AMOUNT_INPUT =
                    "Input " + "Customer" + "'s " + "Total Amount Paid" + ':';

    private static class InstanceHolder {
        private static final InputCustomerTotalPaidAmountMenu INSTANCE = new InputCustomerTotalPaidAmountMenu();
    }
    private InputCustomerTotalPaidAmountMenu() { super(TOTAL_PAID_AMOUNT_INPUT); }
    public static InputCustomerTotalPaidAmountMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected Object inputProperty() throws InputIsEndException, InputMismatchException {
        return inputZeroOrPositiveIntegerOrEnd();
    }

    @Override
    protected void setTempProperty(Object property) throws IllegalArgumentException, IllegalStateException {
        CustomerRepository.getInstance().setTempTotalPaidAmount((Integer) property);
    }
}
