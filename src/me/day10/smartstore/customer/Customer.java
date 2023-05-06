package me.day10.smartstore.customer;

import me.day10.smartstore.group.Group;

public class Customer {

    public static final String ID_FORMAT =
            "ID Format: 4~16 letters consisting of alphabets, digits, underscore(_)";
    private static final int DEFAULT_SPENT_HOURS = 0;
    private static final int DEFAULT_TOTAL_AMOUNT_PAID = 0;
    private static final Group DEFAULT_GROUP = Group.GENERAL;

    private String id;
    private String name;
    private Integer spentHours;
    private Integer totalAmountPaid;
    private Group group;

    public Customer() {
        this(null);
    }

    public Customer(String id) {
        this(id, null, DEFAULT_SPENT_HOURS, DEFAULT_TOTAL_AMOUNT_PAID, DEFAULT_GROUP);
    }

    public Customer(String id, String name, Integer spentHours, Integer totalAmountPaid, Group group) {
        this.id = id;
        this.name = name;
        this.spentHours = spentHours;
        this.totalAmountPaid = totalAmountPaid;
        this.group = group;
    }

    public String getId() { return id; }

    public void setId(String id) throws InvalidCustomerIdException {
        checkIfIdIsValid(id);
        this.id = id;
    }

    public static void checkIfIdIsValid(String id) throws InvalidCustomerIdException {
        if (!isValidId(id))
            throw new InvalidCustomerIdException("Invalid ID input.\n");
    }

    private static boolean isValidId(String id) {
        throwIfIdIsNull(id);
        String pattern = "^[0-9a-zA-Z_]{4,16}$";
        return id.matches(pattern);
    }

    private static void throwIfIdIsNull(String id) { throwIfNull(id, "ID"); }

    private static void throwIfNull(Object o, String title) {
        if (o == null)
            throw new IllegalArgumentException(title + " cannot be null.\n");
    }

    @Override
    public String toString() {
        return String.format("Customer{id='%s', name='%s', spentHours=%d, totalAmountPaid=%d,\n" +
                                "\tgroup=%s",
                                id, name, spentHours, totalAmountPaid, group);
    }
}
