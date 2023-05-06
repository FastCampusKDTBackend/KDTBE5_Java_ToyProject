package me.day10.smartstore.customer;

import me.day10.smartstore.group.Group;

public class Customer {

    public static final String ID_FORMAT =
            "ID Format: 4~16 letters consisting of alphabets, digits, underscore(_)";
    public static final String NAME_FORMAT =
            "Name Format: [1,4] space separated chuck(s) of alphabets of [2,32] lengths.";
    private static final String ID_PATTERN = "^[0-9a-zA-Z_]{4,16}$";
    private static final String NAME_CHUCK = "[a-z]{2,32}";
    private static final String NAME_PATTERN = '^' + NAME_CHUCK + "( " + NAME_CHUCK + "){0,3}$";
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
        this.id = id;
    }

    public void setName(String name) throws InvalidCustomerNameException {
        checkIfNameIsValid(name);
        this.name = name;
    }

    public static void checkIfIdIsValid(String id) throws InvalidCustomerIdException {
        if (!isValidId(id))
            throw new InvalidCustomerIdException("Invalid ID input.\n");
    }

    private static boolean isValidId(String id) {
        throwIfNull(id, "ID");
        return id.matches(ID_PATTERN);
    }

    public static void checkIfNameIsValid(String name) throws InvalidCustomerNameException {
        if (!isValidName(name))
            throw new InvalidCustomerNameException("Invalid Name input.\n");
    }

    private static boolean isValidName(String name) {
        throwIfNull(name, "Name");
        return name.matches(NAME_PATTERN);
    }

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
