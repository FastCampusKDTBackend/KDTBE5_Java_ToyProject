package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.exception.InputIsEndException;

import java.util.InputMismatchException;

public class InputCustomerTotalAmountPaidMenu extends InputCustomerPropertyMenu {

    private static final String TOTAL_AMOUNT_PAID =
                    "Input " + "Customer" + "'s " + "Total Amount Paid" + ':';

    private static class InstanceHolder {
        private static final InputCustomerTotalAmountPaidMenu INSTANCE = new InputCustomerTotalAmountPaidMenu();
    }
    private InputCustomerTotalAmountPaidMenu() { super(TOTAL_AMOUNT_PAID); }
    public static InputCustomerTotalAmountPaidMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected Object inputProperty() throws InputIsEndException, InputMismatchException {
        return inputZeroOrPositiveIntegerOrEnd();
    }

    @Override
    protected void setTempProperty(Object property) throws IllegalArgumentException, IllegalStateException {
        CustomerRepository.getInstance().setTempTotalAmountPaid((Integer) property);
    }
}
