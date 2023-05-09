package me.smartstore.customer;

import java.util.Arrays;
import java.util.InputMismatchException;

public enum Order {
    ASCENDING("A"), DESCENDING("D");

    private final String shortcut;

    Order(String shortcut) {
        this.shortcut = shortcut;
    }

    public static Order getOrderByName(String name) throws InputMismatchException {
        return Arrays.stream(values())
                .filter(order -> order.shortcut.equalsIgnoreCase(name) || order.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new InputMismatchException("Invalid Order name for input. Please try again.\n"));
    }
}
