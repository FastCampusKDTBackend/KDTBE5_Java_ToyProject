package me.smartstore.summary;

import me.smartstore.customer.Customer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public enum Order {

    ASCENDING("A"), DESCENDING("D");

    private final String shortcut;

    Order(String shortcut) { this.shortcut = shortcut; }

    public static final Comparator<Customer> ORDER_BY_NAME_ASC = Comparator.comparing(Customer::getName);
    public static final Comparator<Customer> ORDER_BY_NAME_DEC = ORDER_BY_NAME_ASC.reversed();
    public static final Comparator<Customer> ORDER_BY_SPENT_HOURS_ASC = Comparator.comparing(Customer::getSpentHours);
    public static final Comparator<Customer> ORDER_BY_SPENT_HOURS_DEC = ORDER_BY_SPENT_HOURS_ASC.reversed();
    public static final Comparator<Customer> ORDER_BY_TOTAL_PAID_AMOUNT_ASC = Comparator.comparing(Customer::getTotalPaidAmount);
    public static final Comparator<Customer> ORDER_BY_TOTAL_PAID_AMOUNT_DEC = ORDER_BY_TOTAL_PAID_AMOUNT_ASC.reversed();

    public static Order getOrderByName(String name) throws InputMismatchException {
        return Arrays.stream(values())
                .filter(order -> order.isName(name))
                .findFirst()
                .orElseThrow(() -> new InputMismatchException("Invalid Order name for input. Please try again.\n"));
    }

    private boolean isName(String name) {
        return shortcut.equalsIgnoreCase(name) || name().equalsIgnoreCase(name);
    }
}
