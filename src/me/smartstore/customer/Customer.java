package me.smartstore.customer;

import me.smartstore.group.Group;
import java.util.Comparator;

public class Customer {

    public static final Comparator<Customer> ORDER_NAME_ASC = Comparator.comparing(e -> e.name);
    public static final Comparator<Customer> ORDER_NAME_DEC = (e1, e2) -> e2.name.compareTo(e1.name);
    public static final Comparator<Customer> ORDER_SPENT_HOURS_ASC = Comparator.comparing(e -> e.spentHours);
    public static final Comparator<Customer> ORDER_SPENT_HOURS_DEC = (e1, e2) -> e2.spentHours.compareTo(e1.spentHours);
    public static final Comparator<Customer> ORDER_TOTAL_PAID_AMOUNT_ASC = Comparator.comparing(e -> e.totalAmountPaid);
    public static final Comparator<Customer> ORDER_TOTAL_PAID_AMOUNT_DEC = (e1, e2) -> e2.totalAmountPaid.compareTo(e1.totalAmountPaid);
    public static final String ID_FORMAT =
            "ID Format: 4~16 letters consisting of alphabets, digits, underscore(_)";
    public static final String NAME_FORMAT =
            "Name Format: [1,4] space separated chuck(s) of alphabets of [2,32] lengths.";
    private static final String ID_PATTERN = "^[0-9a-zA-Z_]{4,16}$";
    private static final String NAME_CHUCK = "[a-z]{2,32}";
    private static final String NAME_PATTERN = '^' + NAME_CHUCK + "( " + NAME_CHUCK + "){0,3}$";
    private static final int DEFAULT_SPENT_HOURS = 0;
    private static final int DEFAULT_TOTAL_AMOUNT_PAID = 0;
    private static final Group DEFAULT_GROUP = Group.NONE;


    private String id;
    private String name;
    private Integer spentHours;
    private Integer totalAmountPaid;
    private Group group;

    public Customer(String id, String name) {
        this(id, name, DEFAULT_SPENT_HOURS, DEFAULT_TOTAL_AMOUNT_PAID, DEFAULT_GROUP);
    }

    public Customer(String id, String name, Integer spentHours, Integer totalAmountPaid, Group group) {
        this.id = id;
        this.name = name;
        this.spentHours = spentHours;
        this.totalAmountPaid = totalAmountPaid;
        this.group = group;
    }

    public Customer(Customer e) {
        copy(e);
    }

    public String getId() { return id; }

    public void setId(String id) throws InvalidCustomerIdException {
        this.id = id;
    }

    public void setName(String name) throws InvalidCustomerNameException {
        this.name = name;
    }

    public void setSpentHours(Integer spentHours) {
        this.spentHours = spentHours;
    }

    public void setTotalAmountPaid(Integer totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
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

    public void copy(Customer e) {
        id = e.id;
        name = e.name;
        spentHours = e.spentHours;
        totalAmountPaid = e.totalAmountPaid;
        group = e.group;
    }

    public Group getGroup() { return group; }

    @Override
    public String toString() {
        return String.format("Customer{id='%s', name='%s', spentHours=%d, totalAmountPaid=%d, group=%s}",
                                id, name, spentHours, totalAmountPaid, group.name());
    }
}
