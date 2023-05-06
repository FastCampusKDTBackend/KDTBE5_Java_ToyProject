package me.day10.smartstore.customer;

import me.day10.smartstore.group.Group;

public class Customer {

    private static final int DEFAULT_SPENT_HOURS = 0;
    private static final int DEFAULT_TOTAL_AMOUNT_PAID = 0;
    private static final Group DEFAULT_GROUP = Group.GENERAL;

    private String id;
    private String name;
    private Integer spentHours;
    private Integer totalAmountPaid;
    private Group group;

    public Customer(String id) {
        this(id, null, DEFAULT_SPENT_HOURS, DEFAULT_TOTAL_AMOUNT_PAID, DEFAULT_GROUP);
    }

    public Customer(String id, String name, Integer spentHours, Integer totalAmountPaid, Group group) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null.\n");

        this.name = name;
        this.spentHours = spentHours;
        this.totalAmountPaid = totalAmountPaid;
        this.group = group;
    }

    @Override
    public String toString() {
        return String.format("Customer{id='%s', name='%s', spentHours=%d, totalAmountPaid=%d,\n" +
                                "\tgroup=%s",
                                id, name, spentHours, totalAmountPaid, group);
    }
}
